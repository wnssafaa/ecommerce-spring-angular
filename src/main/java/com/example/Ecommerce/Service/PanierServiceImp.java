package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.LignePanier;
import com.example.Ecommerce.Entities.Panier;

public interface PanierServiceImp {
    Panier CreerPanierPourUtilisateur(Long userId);
    Panier getPanierParUtilisateur(Long userId);
    LignePanier ajouterProduitAuPanier(Long userId, Long produitId, int quantite);
    LignePanier modifierQuantiteProduit(Long userId, Long produitId, int nouvelleQuantite);
    String supprimerProduitDuPanier(Long userId, Long produitId);
}
