package com.example.Ecommerce.controlleur;

import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Category")  // Préfixe pour toutes les routes liées aux catégories
public class CategoryControlleur {

    // Injection du service pour manipuler les catégories
    private final CategoryService service;

    public CategoryControlleur(CategoryService service) {
        this.service = service;
    }

    // Récupérer toutes les catégories
    @GetMapping
    public List<Categorie> allCategories() {
        return service.allCategory(); // Récupère toutes les catégories via le service
    }

    // Ajouter une catégorie
    @PostMapping("/add")
    public String addCategory(@RequestBody Categorie categorie) {
        return service.addCategory(categorie);  // Envoie la catégorie à la couche service
    }

    // Supprimer une catégorie par ID
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id) {
        return service.DeleteCategory(id); // Supprime la catégorie via le service en utilisant l'ID
    }

    // Mettre à jour une catégorie
    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") int id, @RequestBody Categorie categorie) {
        categorie.setId((long) id);  // Assurez-vous que l'ID de la catégorie dans l'URL est utilisé pour la mise à jour
        return service.updateCategory(categorie); // Met à jour la catégorie via le service
    }

    // Récupérer une catégorie par ID (si nécessaire)
    @GetMapping("/{id}")
    public Categorie getCategoryById(@PathVariable("id") int id) {
        return service.CategoryById(id); // Récupère une catégorie par son ID via le service
    }
}
