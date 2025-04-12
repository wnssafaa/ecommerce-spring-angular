package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.*;
import com.example.Ecommerce.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService implements CommendeServiceImp {

    private final CommandeRepository commandeRepository;
    private final UserRepository userRepository;
    private final PanierRepository panierRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository, UserRepository userRepository, PanierRepository panierRepository) {
        this.commandeRepository = commandeRepository;
        this.userRepository = userRepository;
        this.panierRepository = panierRepository;
    }

    @Override
    public Commande creerCommande(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Utilisateur non trouvé");
        }

        User user = userOptional.get();

        Panier panier = panierRepository.findByUtilisateur(user);
        if (panier == null || panier.getLignes().isEmpty()) {
            throw new RuntimeException("Le panier est vide !");
        }

        // Créer la commande
        Commande commande = new Commande();
        commande.setDateCommande(LocalDate.now());
        commande.setUtilisateur(user);

        List<LigneCommande> lignesCommande = new ArrayList<>();

        for (LignePanier lignePanier : panier.getLignes()) {
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setProduit(lignePanier.getProduit());
            ligneCommande.setQuantite(lignePanier.getQuantite());
            ligneCommande.setCommande(commande);
            lignesCommande.add(ligneCommande);
        }

        commande.setLignes(lignesCommande);

        // Vider le panier
        panier.getLignes().clear();
        panierRepository.save(panier);

        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> getCommandesParUtilisateur(Long userId) {
        return commandeRepository.findByUtilisateurId(userId);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec ID : " + id));
    }
}
