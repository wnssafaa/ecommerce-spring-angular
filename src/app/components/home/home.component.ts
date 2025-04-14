import { Component, OnInit } from '@angular/core';
import { BreakpointObserver } from '@angular/cdk/layout';
import { CommonModule } from '@angular/common';
import { ProduitService, Produit } from '../../services/produit.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatGridListModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isLargeScreen = true;
  produits: Produit[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(
    private produitService: ProduitService,
    private breakpointObserver: BreakpointObserver
  ) {
    this.breakpointObserver.observe(['(min-width: 768px)'])
      .subscribe(result => {
        this.isLargeScreen = result.matches;
      });
  }

  ngOnInit(): void {
    this.produitService.getAllProduits().subscribe({
      next: (produits) => {
        this.produits = produits;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur de chargement:', err);
        this.errorMessage = 'Impossible de charger les produits';
        this.isLoading = false;
      }
    });
  }
}