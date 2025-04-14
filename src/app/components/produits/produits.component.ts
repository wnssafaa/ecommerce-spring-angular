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
export class ProduitsComponent implements OnInit {

  produitForm!: FormGroup;
  produits: Produit[] = [];
  categories: Categorie[] = [];

  constructor(
    private produitService: ProduitService,
    private categorieService: CategorieService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.getProduits();
    this.getCategories();
  }

  initForm(): void {
    this.produitForm = this.fb.group({
      nom: ['', Validators.required],
      description: [''],
      prix: [0, [Validators.required, Validators.min(0)]],
      quantite: [0, [Validators.required, Validators.min(0)]],
      categorieId: [null, Validators.required]
    });
  }

  getProduits(): void {
    this.produitService.getAllProduits().subscribe(data => {
      this.produits = data;
    });
  }

  getCategories(): void {
    this.categorieService.getAllCategories().subscribe(data => {
      this.categories = data;
    });
  }

  onSubmit(): void {
    if (this.produitForm.valid) {
      this.produitService.ajouterProduit(this.produitForm.value).subscribe(() => {
        this.produitForm.reset();
        this.getProduits(); // refresh list
      });
    }
  }

  supprimerProduit(id: number): void {
    this.produitService.supprimerProduit(id).subscribe(() => {
      this.getProduits();
    });
  }
}