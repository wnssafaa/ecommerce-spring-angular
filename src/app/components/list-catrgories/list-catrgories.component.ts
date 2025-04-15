import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { Categorie, CategorieService } from '../../services/categorie.service';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmDialogComponent } from '../../dialogs/confirm-dialog/confirm-dialog.component';
@Component({
  selector: 'app-list-catrgories',
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,MatProgressSpinnerModule
  ],
  templateUrl: './list-catrgories.component.html',
  styleUrl: './list-catrgories.component.css'
})
export class ListCatrgoriesComponent implements OnInit {
  dataSource = new MatTableDataSource<Categorie>();
  displayedColumns: string[] = ['id', 'nom', 'actions'];
  loading = true;
  errorMessage = '';
  searchControl = new FormControl('');

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private categorieService: CategorieService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.setupSearch();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  loadCategories(): void {
    this.loading = true;
    this.categorieService.getAllCategories().subscribe({
      next: (data: Categorie[]) => {
        this.dataSource.data = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.errorMessage = 'Échec du chargement des catégories';
        this.loading = false;
        console.error(err);
      }
    });
  }

  setupSearch(): void {
    this.searchControl.valueChanges.subscribe(value => {
      this.dataSource.filter = value?.trim().toLowerCase() || '';
    });
  }
  deleteCategory(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'Confirmation de suppression',
        message: 'Êtes-vous sûr de vouloir supprimer cette catégorie ?'
      },
      width: '500px'
    });
  
    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this.categorieService.deleteCategory(id).subscribe({
          next: () => {
            this.dataSource.data = this.dataSource.data.filter(cat => cat.id !== id);
          },
          error: (err) => {
            this.errorMessage = 'Échec de la suppression';
            console.error(err);
          }
        });
      }
    });}

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openEditDialog(categorie: Categorie): void {
    this.router.navigate(['../categorie', categorie.id], { relativeTo: this.route });
  }

  openAddDialog(): void {
    this.router.navigate(['../categorie',]);
  }
  
}
