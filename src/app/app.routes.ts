import { Routes } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProduitsComponent } from './components/produits/produits.component';
import { HomeComponent } from './components/home/home.component';
import {CategoriesComponent  } from './components/categories/categories.component';
import { ListCatrgoriesComponent } from './components/list-catrgories/list-catrgories.component';

export const routes: Routes = [
    { path: 'navbar', component: NavbarComponent, },
    { path: 'product', component: ProduitsComponent, },
    { path: 'categorie', component:CategoriesComponent, },
    { path: 'categories', component:ListCatrgoriesComponent, },
    { path: 'home', component: HomeComponent, },
    { path: '', redirectTo: '/home', pathMatch: 'full' },
];
