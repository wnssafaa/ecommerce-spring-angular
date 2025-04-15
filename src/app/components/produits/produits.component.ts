import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Produit, ProduitService } from '../../services/produit.service';
import { Categorie, CategorieService } from '../../services/categorie.service';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDialog } from '@angular/material/dialog';
import { MatSelect } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../dialogs/confirm-dialog/confirm-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-produits',
  imports: [ReactiveFormsModule, CommonModule,MatTableModule,MatFormFieldModule,MatSelect,MatSelectModule,MatInputModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatGridListModule,
    MatProgressSpinnerModule],
  templateUrl: './produits.component.html',
  styleUrl: './produits.component.css'
})
export class ProduitsComponent  implements OnInit {
  produitForm!: FormGroup;

  private initForm(): void {
    this.produitForm = this.fb.group({
      nom: ['', Validators.required],
      description: [''],
      prix: [0, [Validators.required, Validators.min(0)]],
      quantite: [0, [Validators.required, Validators.min(0)]],
      categorieId: [null, Validators.required]
    });
  }
  produits: Produit[] = [];
  categories: Categorie[] = [];
  isEditMode = false;
  isLoading = false;

  constructor(
    private produitService: ProduitService,
    private categorieService: CategorieService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    public router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.initForm();
   this.categorieService.getAllCategories().subscribe({
     next: (categories) => this.categories = categories,
     error: () => this.showError('Erreur de chargement des catégories')
   });
    this.checkEditMode();
  }

  private checkEditMode(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEditMode = true;
      this.loadProduit(+id);
    }
  }

  private loadProduit(id: number): void {
    this.produitService.getProduitById(id).subscribe({
      next: (produit) => this.produitForm.patchValue(produit),
      error: () => this.showError('Erreur de chargement du produit')
    });
  }

  onSubmit(): void {
    if (this.produitForm.invalid || this.isLoading) return;

    this.isLoading = true;
    const produit = this.produitForm.value;

    const operation = this.isEditMode 
      ? this.produitService.modifierProduit(this.route.snapshot.params['id'], produit)
      : this.produitService.ajouterProduit(produit);

    operation.subscribe({
      next: () => {
        this.handleSuccess();
        this.router.navigate(['/produits']);
      },
      error: (err) => this.handleError(err)
    });
  }

  supprimerProduit(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { 
        title: 'Confirmer la suppression',
        message: 'Êtes-vous sûr de vouloir supprimer ce produit ?'
      }
    });

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this.produitService.supprimerProduit(id).subscribe({
          next: () => {
            this.produits;
            this.showSuccess('Produit supprimé avec succès');
          },
          error: () => this.showError('Erreur lors de la suppression')
        });
      }
    });
  }

  private handleSuccess(): void {
    this.isLoading = false;
    const message = this.isEditMode 
      ? 'Produit modifié avec succès' 
      : 'Produit ajouté avec succès';
    this.showSuccess(message);
  }

  private handleError(error: any): void {
    this.isLoading = false;
    console.error(error);
    this.showError(error.error?.message || 'Erreur inconnue');
  }

  private showSuccess(message: string): void {
    this.snackBar.open(message, 'Fermer', { duration: 3000 });
  }

  private showError(message: string): void {
    this.snackBar.open(message, 'Fermer', { 
      duration: 5000,
      panelClass: ['error-snackbar']
    });
  }
}