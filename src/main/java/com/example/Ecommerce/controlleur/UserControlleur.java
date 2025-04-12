package com.example.Ecommerce.controlleur;

import com.example.Ecommerce.Entities.User;
import com.example.Ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControlleur {

    private final UserService service;

    @Autowired
    public UserControlleur(UserService service) {
        this.service = service;
    }

    // ➕ Ajouter un utilisateur
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    // 📜 Afficher tous les utilisateurs
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // 🔍 Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // 🔄 Mettre à jour un utilisateur
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    // ❌ Supprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }
}
