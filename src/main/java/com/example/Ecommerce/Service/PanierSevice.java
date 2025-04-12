package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.LignePanier;
import com.example.Ecommerce.Entities.Panier;
import com.example.Ecommerce.Entities.Produit;
import com.example.Ecommerce.Entities.User;
import com.example.Ecommerce.Repository.LignePanierRepository;
import com.example.Ecommerce.Repository.PanierRepository;
import com.example.Ecommerce.Repository.ProduitRepository;
import com.example.Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierSevice implements PanierServiceImp {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private LignePanierRepository lignePanierRepository;

    @Override
    public Panier CreerPanierPourUtilisateur(Long userId) {
        Optional<User> utilisateur = userRepository.findById(userId);
        if (utilisateur.isPresent()) {
            Panier panier = new Panier();
            panier.setUtilisateur(utilisateur.get());
            return panierRepository.save(panier);
        }
        return null;
    }

    @Override
    public Panier getPanierParUtilisateur(Long userId) {
        return panierRepository.findByUtilisateurId(userId);
    }

    @Override
    public LignePanier ajouterProduitAuPanier(Long userId, Long produitId, int quantite) {
        Panier panier = panierRepository.findByUtilisateurId(userId);
        Optional<Produit> produitOpt = produitRepository.findById(produitId);

        if (panier != null && produitOpt.isPresent()) {
            Produit produit = produitOpt.get();
            LignePanier ligne = new LignePanier(produit, quantite);
            ligne.setPanier(panier);
            panier.getLignes().add(ligne);
            panierRepository.save(panier);
            return lignePanierRepository.save(ligne);
        }
        return null;
    }

    @Override
    public LignePanier modifierQuantiteProduit(Long userId, Long produitId, int nouvelleQuantite) {
        Panier panier = panierRepository.findByUtilisateurId(userId);
        if (panier != null) {
            for (LignePanier ligne : panier.getLignes()) {
                if (ligne.getProduit().getId().equals(produitId)) {
                    ligne.setQuantite(nouvelleQuantite);
                    return lignePanierRepository.save(ligne);
                }
            }
        }
        return null;
    }

    @Override
    public String supprimerProduitDuPanier(Long userId, Long produitId) {
        Panier panier = panierRepository.findByUtilisateurId(userId);
        if (panier != null) {
            LignePanier ligneASupprimer = null;
            for (LignePanier ligne : panier.getLignes()) {
                if (ligne.getProduit().getId().equals(produitId)) {
                    ligneASupprimer = ligne;
                    break;
                }
            }
            if (ligneASupprimer != null) {
                panier.getLignes().remove(ligneASupprimer);
                lignePanierRepository.delete(ligneASupprimer);
                panierRepository.save(panier);
                return "Produit supprimé du panier.";
            }
        }
        return "Produit non trouvé dans le panier.";
    }
}
