package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Entities.Produit;
import com.example.Ecommerce.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService implements ProduitServiceImp {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public String addProduit(Produit p) {
        try {
            produitRepository.save(p);
            return "Produit ajouté avec succès.";
        } catch (Exception e) {
            return "Erreur lors de l'ajout du produit : " + e.getMessage();
        }
    }

    @Override
    public String updateProduit(Produit p) {
        try {
            if (produitRepository.existsById(p.getId())) {
                produitRepository.save(p);
                return "Produit mis à jour avec succès.";
            } else {
                return "Produit non trouvé.";
            }
        } catch (Exception e) {
            return "Erreur lors de la mise à jour du produit : " + e.getMessage();
        }
    }

    @Override
    public String DeleteProduit(long id) {
        try {
            if (produitRepository.existsById(id)) {
                produitRepository.deleteById(id);
                return "Produit supprimé avec succès.";
            } else {
                return "Produit non trouvé.";
            }
        } catch (Exception e) {
            return "Erreur lors de la suppression du produit : " + e.getMessage();
        }
    }

    @Override
    public List<Produit> Produites() {
        return produitRepository.findAll();
    }

    @Override
    public Produit ProduitById(long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        return produit.orElse(null);
    }

    @Override
    public Produit ProduitByCategory(Categorie c) {
        return produitRepository.findByCategorie(c); // Cette méthode dépend de la structure de votre repository
    }

    @Override
    public String updateProductQuantity(long id, int newQuantity) {
        Optional<Produit> produitOpt = produitRepository.findById(id);
        if (produitOpt.isPresent()) {
            Produit produit = produitOpt.get();
            produit.setQuantite(newQuantity);
            produitRepository.save(produit);
            return "Quantité mise à jour avec succès.";
        } else {
            return "Produit non trouvé.";
        }
    }

    @Override
    public boolean checkProductAvailability(long id, int quantity) {
        Optional<Produit> produitOpt = produitRepository.findById(id);
        if (produitOpt.isPresent()) {
            Produit produit = produitOpt.get();
            return produit.getQuantite() >= quantity;
        }
        return false;
    }

    @Override
    public List<Produit> searchProductByName(String name) {
        return produitRepository.findByNomContainingIgnoreCase(name); // Recherche insensible à la casse
    }

    @Override
    public String updateProductPrice(long id, double newPrice) {
        Optional<Produit> produitOpt = produitRepository.findById(id);
        if (produitOpt.isPresent()) {
            Produit produit = produitOpt.get();
            produit.setPrix(newPrice);
            produitRepository.save(produit);
            return "Prix mis à jour avec succès.";
        } else {
            return "Produit non trouvé.";
        }
    }
}
