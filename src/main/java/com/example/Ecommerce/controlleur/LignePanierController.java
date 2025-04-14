package com.example.Ecommerce.controlleur;

import com.example.Ecommerce.Entities.LignePanier;
import com.example.Ecommerce.Service.LignePanierServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ligne-panier")
public class LignePanierController {

    @Autowired
    private LignePanierServiceImp service;

    // Modifier la quantité d'une ligne panier
    @PutMapping("/modifier/{id}")
    public LignePanier modifierQuantite(
            @PathVariable int id,
            @RequestParam int quantite
    ) {
        return service.mettreAJourQuantite(id, quantite);
    }

    // Supprimer une ligne panier
    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable int id) {
        service.supprimerLigne(id);
    }
}
