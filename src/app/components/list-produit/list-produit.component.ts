import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Produit, ProduitService } from '../../services/produit.service';
import { Categorie, CategorieService } from '../../services/categorie.service';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmDialogComponent } from '../../dialogs/confirm-dialog/confirm-dialog.component';
import { CommonModule } from '@angular/common';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatInput, MatInputModule } from '@angular/material/input';
import { MatSelect, MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
@Component({
  selector: 'app-list-produit',
  imports: [CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    FormsModule,MatPaginatorModule, MatTableModule,MatProgressSpinnerModule,
    ReactiveFormsModule],
  templateUrl: './list-produit.component.html',
  styleUrl: './list-produit.component.css'
})
export class ListProduitComponent implements OnInit {
  displayedColumns: string[] = ['id', 'nom', 'description', 'prix', 'quantite', 'categorieId', 'actions'];
  dataSource!: MatTableDataSource<Produit>;
  searchText: string = '';
  selectedCategory: number | null = null;
  categories: Categorie[] = [];
  isLoading = true;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private produitService: ProduitService,
    private categorieService: CategorieService,
    private dialog: MatDialog,
    public router: Router, private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.loadProduits();
    this.loadCategories();
  }

  loadProduits(): void {
    this.isLoading = true;
    this.produitService.getAllProduits().subscribe({
      next: (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading products:', err);
        this.isLoading = false;
      }
    });
  }

  loadCategories(): void {
    this.categorieService.getAllCategories().subscribe(categories => {
      this.categories = categories;
    });
  }

  applyFilter(): void {
    this.dataSource.filter = JSON.stringify({
      searchText: this.searchText.toLowerCase(),
      categoryId: this.selectedCategory
    });
  }

  deleteProduit(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'Confirmer la suppression',
        message: 'Êtes-vous sûr de vouloir supprimer ce produit ?'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.produitService.supprimerProduit(id).subscribe({
          next: () => {
            this.dataSource.data = this.dataSource.data.filter(p => p.id !== id);
          },
          error: (err) => console.error('Error deleting product:', err)
        });
      }
    });
  }

  editProduit(produit:Produit): void {
    this.router.navigate(['../product', produit.id]);
  }

  // Custom filter predicate pour la recherche et le filtrage par catégorie
  initializeFilter(): void {
    this.dataSource.filterPredicate = (data: Produit, filter: string) => {
      const filterData = JSON.parse(filter);
      const matchesSearch = data.nom.toLowerCase().includes(filterData.searchText) ||
                            data.description.toLowerCase().includes(filterData.searchText);
      const matchesCategory = !filterData.categoryId || data.categorieId === filterData.categoryId;
      return matchesSearch && matchesCategory;
    };
  }

  resetFilters(): void {
    this.searchText = '';
    this.selectedCategory = null;
    this.applyFilter();
  }
}
