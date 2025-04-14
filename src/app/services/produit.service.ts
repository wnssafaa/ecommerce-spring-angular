import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Produit {
  id?: number;
  nom: string;
  description: string;
  prix: number;
  quantite: number;
  // imageUrl?: string;
  categorieId?: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  private apiUrl = 'http://localhost:8787/produit';

  constructor(private http: HttpClient) { }

  // Correction des types de retour pour correspondre au backend
  ajouterProduit(produit: Produit): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/add`, produit);
  }

  modifierProduit(id: number, produit: Produit): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/update/${id}`, produit);
  }

  supprimerProduit(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/delete/${id}`);
  }

  // Méthodes existantes (gardées pour référence)
  getAllProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/all`);
  }

  getProduitById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${this.apiUrl}/${id}`);
  }

  // Nouvelles méthodes pour les fonctionnalités manquantes
  getProduitsByCategory(categoryId: number): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/category/${categoryId}`);
  }

  searchProduitByName(name: string): Observable<Produit[]> {
    const params = new HttpParams().set('name', name);
    return this.http.get<Produit[]>(`${this.apiUrl}/search`, { params });
  }

  updateProductQuantity(id: number, quantity: number): Observable<string> {
    const params = new HttpParams().set('quantity', quantity.toString());
    return this.http.put<string>(`${this.apiUrl}/update/quantity/${id}`, null, { params });
  }

  checkProductAvailability(id: number, quantity: number): Observable<boolean> {
    const params = new HttpParams().set('quantity', quantity.toString());
    return this.http.get<boolean>(`${this.apiUrl}/checkAvailability/${id}`, { params });
  }

  updateProductPrice(id: number, price: number): Observable<string> {
    const params = new HttpParams().set('price', price.toString());
    return this.http.put<string>(`${this.apiUrl}/update/price/${id}`, null, { params });
  }
}