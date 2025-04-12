package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.User;
import java.util.List;

public interface UserImp {

    // Ajouter un nouvel utilisateur
    public User addUser(User user);

    // Mettre à jour les informations d'un utilisateur
    public User updateUser(Long id, User updatedUser);

    // Supprimer un utilisateur
    public String deleteUser(Long id);

    // Récupérer un utilisateur par ID
    public User getUserById(Long id);

    // Récupérer un utilisateur par email
    public User getUserByEmail(String email);

    // Vérifier si un utilisateur est connecté
    public boolean checkIfUserIsConnected(Long id);

    // Authentifier un utilisateur par email et mot de passe
    public User authenticateUser(String email, String password);

    // Déconnecter un utilisateur
    public User logoutUser(Long id);

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers();

    // Assigner un rôle à un utilisateur
    public User assignRoleToUser(Long userId, Long roleId);
}
