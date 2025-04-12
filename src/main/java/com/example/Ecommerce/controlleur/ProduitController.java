package com.example.Ecommerce.controlleur;
import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Entities.Produit;
import com.example.Ecommerce.Service.ProduitServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    private ProduitServiceImp produitService;

    // Ajouter un produit
    @PostMapping("/add")
    public ResponseEntity<String> addProduit(@RequestBody Produit produit) {
        String response = produitService.addProduit(produit);
        return ResponseEntity.ok(response);
    }

    // Mettre à jour un produit
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduit(@PathVariable("id") int id, @RequestBody Produit produit) {
        produit.setId((long) id); // Assurez-vous que l'ID du produit dans le corps de la requête correspond à l'ID de l'URL
        String response = produitService.updateProduit(produit);
        return ResponseEntity.ok(response);
    }

    // Supprimer un produit
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable("id") int id) {
        String response = produitService.DeleteProduit(id);
        return ResponseEntity.ok(response);
    }

    // Récupérer tous les produits
    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.Produites();
        return ResponseEntity.ok(produits);
    }

    // Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") int id) {
        Produit produit = produitService.ProduitById(id);
        if (produit != null) {
            return ResponseEntity.ok(produit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer les produits par catégorie
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Produit>> getProduitsByCategory(@PathVariable("categoryId") int categoryId) {
        Categorie category = new Categorie();
        category.setId((long) categoryId);
        List<Produit> produits = (List<Produit>) produitService.ProduitByCategory(category);
        return ResponseEntity.ok(produits);
    }

    // Rechercher un produit par nom
    @GetMapping("/search")
    public ResponseEntity<List<Produit>> searchProduitByName(@RequestParam("name") String name) {
        List<Produit> produits = produitService.searchProductByName(name);
        return ResponseEntity.ok(produits);
    }

    // Mettre à jour la quantité d'un produit
    @PutMapping("/update/quantity/{id}")
    public ResponseEntity<String> updateProductQuantity(@PathVariable("id") int id, @RequestParam("quantity") int quantity) {
        String response = produitService.updateProductQuantity(id, quantity);
        return ResponseEntity.ok(response);
    }

    // Vérifier la disponibilité d'un produit
    @GetMapping("/checkAvailability/{id}")
    public ResponseEntity<Boolean> checkProductAvailability(@PathVariable("id") int id, @RequestParam("quantity") int quantity) {
        boolean available = produitService.checkProductAvailability(id, quantity);
        return ResponseEntity.ok(available);
    }

    // Mettre à jour le prix d'un produit
    @PutMapping("/update/price/{id}")
    public ResponseEntity<String> updateProductPrice(@PathVariable("id") int id, @RequestParam("price") double price) {
        String response = produitService.updateProductPrice(id, price);
        return ResponseEntity.ok(response);
    }
}