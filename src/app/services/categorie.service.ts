import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

export interface Categorie {
  id?: number;
  nom: string;
}

@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  private apiUrl = 'http://localhost:8787/Category';

  constructor(private http: HttpClient) { }

  // Récupérer toutes les catégories
  getAllCategories(): Observable<Categorie[]> {
    return this.http.get<Categorie[]>(this.apiUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Ajouter une catégorie
  addCategory(categorie: Categorie): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/add`, categorie)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Supprimer une catégorie
  deleteCategory(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/delete/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Mettre à jour une catégorie
  updateCategory(id: number, categorie: Categorie): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/update/${id}`, categorie)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Récupérer une catégorie par ID
  getCategoryById(id: number): Observable<Categorie> {
    return this.http.get<Categorie>(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Gestion centralisée des erreurs
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Une erreur est survenue';
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Erreur: ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = `Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}