package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.LignePanier;
import com.example.Ecommerce.Entities.Panier;
import com.example.Ecommerce.Entities.Produit;

public interface LignePanierServiceImp {
    LignePanier ajouterLigne(Panier panier, Produit produit, int quantite);
    LignePanier mettreAJourQuantite(int idLigne, int nouvelleQuantite);
    void supprimerLigne(int idLigne);
}
