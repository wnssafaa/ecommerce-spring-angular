import { Routes } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProduitsComponent } from './components/produits/produits.component';
import { HomeComponent } from './components/home/home.component';
import {CategoriesComponent  } from './components/categories/categories.component';
import { ListCatrgoriesComponent } from './components/list-catrgories/list-catrgories.component';
import { ListProduitComponent } from './components/list-produit/list-produit.component';

export const routes: Routes = [
    { path: 'navbar', component: NavbarComponent, },
    { path: 'product', component: ProduitsComponent, },
    { path: 'product/:id', component: ProduitsComponent, },
    { path: 'categorie', component:CategoriesComponent, },
    { path: 'categorie/:id', component:CategoriesComponent, },
    { path: 'categories', component:ListCatrgoriesComponent, },
    { path: 'productes', component:ListProduitComponent, },
    
    { path: 'home', component: HomeComponent, },

    { path: '', redirectTo: '/home', pathMatch: 'full' },
];
