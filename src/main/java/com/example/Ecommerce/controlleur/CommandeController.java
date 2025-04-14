package com.example.Ecommerce.controlleur;

import com.example.Ecommerce.Entities.Commande;
import com.example.Ecommerce.Service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/commande")
public class CommandeController {

    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    // ➕ Créer une commande pour un utilisateur (à partir de son panier)
    @PostMapping("/add/{userId}")
    public Commande creerCommande(@PathVariable Long userId) {
        return commandeService.creerCommande(userId);
    }

    // 📄 Lister toutes les commandes
    @GetMapping("/all")
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    // 📋 Obtenir les commandes d’un utilisateur
    @GetMapping("/user/{userId}")
    public List<Commande> getCommandesParUtilisateur(@PathVariable Long userId) {
        return commandeService.getCommandesParUtilisateur(userId);
    }

    // 🔍 Détail d’une commande
    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }
}
