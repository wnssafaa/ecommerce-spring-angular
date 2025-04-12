package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.Commande;

import java.util.List;

public interface CommendeServiceImp {
    Commande creerCommande(Long userId);
    List<Commande> getCommandesParUtilisateur(Long userId);
    List<Commande> getAllCommandes();
    Commande getCommandeById(Long id);
}
