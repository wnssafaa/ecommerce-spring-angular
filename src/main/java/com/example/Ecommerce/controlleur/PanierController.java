package com.example.Ecommerce.controlleur;

import com.example.Ecommerce.Entities.LignePanier;
import com.example.Ecommerce.Entities.Panier;
import com.example.Ecommerce.Service.PanierServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/panier")
public class PanierController {

    @Autowired
    private PanierServiceImp panierService;

    // Créer un panier pour un utilisateur
    @PostMapping("/creer/{userId}")
    public Panier creerPanier(@PathVariable Long userId) {
        return panierService.CreerPanierPourUtilisateur(userId);
    }

    // Obtenir le panier d’un utilisateur
    @GetMapping("/utilisateur/{userId}")
    public Panier getPanier(@PathVariable Long userId) {
        return panierService.getPanierParUtilisateur(userId);
    }

    // Ajouter un produit au panier
    @PostMapping("/ajouter")
    public LignePanier ajouterProduit(
            @RequestParam Long userId,
            @RequestParam Long produitId,
            @RequestParam int quantite
    ) {
        return panierService.ajouterProduitAuPanier(userId, produitId, quantite);
    }

    // Modifier la quantité d’un produit
    @PutMapping("/modifier")
    public LignePanier modifierQuantite(
            @RequestParam Long userId,
            @RequestParam Long produitId,
            @RequestParam int quantite
    ) {
        return panierService.modifierQuantiteProduit(userId, produitId, quantite);
    }

    // Supprimer un produit du panier
    @DeleteMapping("/supprimer")
    public String supprimerProduit(
            @RequestParam Long userId,
            @RequestParam Long produitId
    ) {
        return panierService.supprimerProduitDuPanier(userId, produitId);
    }
}
