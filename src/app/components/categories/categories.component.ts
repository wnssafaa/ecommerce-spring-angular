import { Component, OnInit } from '@angular/core';
import { Categorie, CategorieService } from '../../services/categorie.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../dialogs/confirm-dialog/confirm-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatListModule } from '@angular/material/list';
@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [
    CommonModule,MatProgressSpinnerModule,MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSnackBarModule,
    MatIconModule,
    MatDialogModule
  ],
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  // Liste des catégories
  categories: Categorie[] = [];
  
  // Formulaire réactif
  categoryForm: FormGroup;
  
  // États du composant
  isSubmitting = false;
  currentCategoryId: number | null = null;
  isEditing = false;
  isLoading = false;

  constructor(
    private categorieService: CategorieService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private route: ActivatedRoute,
    public router: Router
  ) {
    // Initialisation du formulaire avec validation
    this.categoryForm = this.fb.group({
      nom: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  ngOnInit() {
    // Vérification des paramètres de route pour l'édition
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.handleEditMode(+id);
      } else {
        this.loadCategories();
      }
    });
  }

  // Charge les catégories depuis l'API
  loadCategories() {
    this.isLoading = true;
    this.categorieService.getAllCategories().subscribe({
      next: (data) => {
        this.categories = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.showError('Erreur de chargement des catégories');
        this.isLoading = false;
      }
    });
  }

  // Gère la soumission du formulaire
  onSubmit() {
    if (this.categoryForm.valid && !this.isSubmitting) {
      this.isSubmitting = true;
      const categoryData = this.categoryForm.value;

      if (this.isEditing && this.currentCategoryId) {
        this.updateCategory(categoryData);
      } else {
        this.addCategory(categoryData);
      }
    }
  }

  // Ajoute une nouvelle catégorie
  private addCategory(categoryData: any) {
    this.categorieService.addCategory(categoryData).subscribe({
      next: () => this.handleSuccess('Catégorie ajoutée avec succès', false),
      error: (err) => this.handleError('Erreur lors de l\'ajout', err)
    });
  }

  // Met à jour une catégorie existante
  private updateCategory(categoryData: any) {
    if (!this.currentCategoryId) return;

    this.categorieService.updateCategory(this.currentCategoryId, categoryData).subscribe({
      next: () => this.handleSuccess('Catégorie modifiée avec succès', true),
      error: (err) => this.handleError('Erreur lors de la modification', err)
    });
  }

  // Gère le mode édition via l'URL
  private handleEditMode(id: number) {
    this.isLoading = true;
    this.isEditing = true;
    this.currentCategoryId = id;

    this.categorieService.getCategoryById(id).subscribe({
      next: (category) => {
        this.categoryForm.patchValue(category);
        this.isLoading = false;
      },
      error: (err) => {
        this.showError('Catégorie introuvable');
        this.router.navigate(['../']);
        this.isLoading = false;
      }
    });
  }

  // Annule l'édition
  cancelEdit() {
    this.isEditing = false;
    this.currentCategoryId = null;
    this.categoryForm.reset();
    this.router.navigate(['../']);
  }

  // Confirmation de suppression
  confirmDelete(categoryId: number) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { 
        title: 'Confirmer la suppression',
        message: 'Êtes-vous sûr de vouloir supprimer cette catégorie ?'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteCategory(categoryId);
      }
    });
  }

  // Supprime une catégorie
  private deleteCategory(categoryId: number) {
    this.categorieService.deleteCategory(categoryId).subscribe({
      next: () => this.handleSuccess('Catégorie supprimée avec succès', false),
      error: (err) => this.handleDeleteError(err)
    });
  }

  // Gestion des erreurs de suppression
  private handleDeleteError(error: any) {
    const message = error.status === 404 ? 
      'Catégorie introuvable' : 
      'Erreur lors de la suppression';
    this.showError(message);
  }

  // Affiche les détails rapides
  viewDetails(category: Categorie) {
    this.snackBar.open(
      `Détails de la catégorie: ${category.nom} (ID: ${category.id})`, 
      'Fermer', 
      { duration: 3000 }
    );
  }

  // Gère les succès
  private handleSuccess(message: string, isEdit: boolean) {
    this.snackBar.open(message, 'Fermer', { duration: 3000 });
    this.loadCategories();
    this.isSubmitting = false;
    
    if (isEdit) {
      this.router.navigate(['../']);
    } else {
      this.categoryForm.reset();
    }
  }

  // Gère les erreurs
  private handleError(message: string, error: any) {
    console.error(error);
    this.showError(message);
    this.isSubmitting = false;
  }

  // Affiche les erreurs
  private showError(message: string) {
    this.snackBar.open(message, 'Fermer', {
      duration: 5000,
      panelClass: ['error-snackbar']
    });
  }
}