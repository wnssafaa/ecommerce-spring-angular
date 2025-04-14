import { Component } from '@angular/core';
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

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [
    CommonModule,
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
export class CategoriesComponent {
  categories: Categorie[] = [];
  categoryForm: FormGroup;
  isSubmitting = false;
  currentCategoryId: number | null = null;
  isEditing = false;

  constructor(
    private categorieService: CategorieService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.categoryForm = this.fb.group({
      nom: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.loadCategories();
  }

  loadCategories() {
    this.categorieService.getAllCategories().subscribe({
      next: (data) => this.categories = data,
      error: (err) => this.showError('Erreur de chargement des catégories')
    });
  }

  onSubmit() {
    if (this.categoryForm.valid && !this.isSubmitting) {
      this.isSubmitting = true;
      const categoryData = this.categoryForm.value;

      if (this.isEditing && this.currentCategoryId) {
        this.categorieService.updateCategory(this.currentCategoryId, categoryData).subscribe({
          next: () => {
            this.handleSuccess('Catégorie modifiée avec succès');
            this.cancelEdit();
          },
          error: (err) => this.handleError('Erreur lors de la modification', err)
        });
      } else {
        this.categorieService.addCategory(categoryData).subscribe({
          next: () => {
            this.handleSuccess('Catégorie ajoutée avec succès');
            this.categoryForm.reset();
          },
          error: (err) => this.handleError('Erreur lors de l\'ajout', err)
        });
      }
    }
  }

  startEdit(category: Categorie) {
    this.isEditing = true;
    this.currentCategoryId = category.id!;
    this.categoryForm.patchValue({
      nom: category.nom
    });
  }

  cancelEdit() {
    this.isEditing = false;
    this.currentCategoryId = null;
    this.categoryForm.reset();
  }

  // Dans CategoriesComponent
confirmDelete(categoryId: number) {
  const dialogRef = this.dialog.open(ConfirmDialogComponent, {
    data: { 
      title: 'Confirmer la suppression',
      message: 'Êtes-vous sûr de vouloir supprimer cette catégorie ?'
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result) {
      this.categorieService.deleteCategory(categoryId).subscribe({
        next: () => this.handleSuccess('Catégorie supprimée avec succès'),
        error: (err) => {
          const message = err.message.includes('404') ? 
            'Catégorie introuvable' : 
            'Erreur lors de la suppression';
          this.showError(message);
        }
      });
    }
  });
}

  viewDetails(category: Categorie) {
    this.snackBar.open(`Détails de la catégorie: ${category.nom} (ID: ${category.id})`, 'Fermer', {
      duration: 3000
    });
  }

  private handleSuccess(message: string) {
    this.snackBar.open(message, 'Fermer', { duration: 3000 });
    this.loadCategories();
    this.isSubmitting = false;
  }

  private handleError(message: string, error: any) {
    console.error(error);
    this.showError(message);
    this.isSubmitting = false;
  }

  private showError(message: string) {
    this.snackBar.open(message, 'Fermer', {
      duration: 5000,
      panelClass: ['error-snackbar']
    });
  }
}