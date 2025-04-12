package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.LignePanier;
import com.example.Ecommerce.Entities.Panier;
import com.example.Ecommerce.Entities.Produit;
import com.example.Ecommerce.Repository.LignePanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LignePanierService implements LignePanierServiceImp {

    @Autowired
    private LignePanierRepository lignePanierRepository;

    @Override
    public LignePanier ajouterLigne(Panier panier, Produit produit, int quantite) {
        LignePanier ligne = new LignePanier();
        ligne.setPanier(panier);
        ligne.setProduit(produit);
        ligne.setQuantite(quantite);
        return lignePanierRepository.save(ligne);
    }

    @Override
    public LignePanier mettreAJourQuantite(int idLigne, int nouvelleQuantite) {
        Optional<LignePanier> ligneOpt = lignePanierRepository.findById((long) idLigne);
        if (ligneOpt.isPresent()) {
            LignePanier ligne = ligneOpt.get();
            ligne.setQuantite(nouvelleQuantite);
            return lignePanierRepository.save(ligne);
        }
        return null;
    }

    @Override
    public void supprimerLigne(int idLigne) {
        lignePanierRepository.deleteById((long) idLigne);
    }
}
