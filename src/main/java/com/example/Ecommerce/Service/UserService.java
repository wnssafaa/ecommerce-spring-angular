package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.User;
import com.example.Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserImp {

    @Autowired
    private UserRepository userRepository;

    // Ajouter un nouvel utilisateur
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Mettre à jour les informations d'un utilisateur
    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setPrenom(updatedUser.getPrenom());
            user.setAge(updatedUser.getAge());
            user.setDateNaissance(updatedUser.getDateNaissance());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            user.setConnected(updatedUser.isConnected());
            return userRepository.save(user);
        }
        return null; // L'utilisateur n'existe pas
    }

    // Supprimer un utilisateur
    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

    // Récupérer un utilisateur par ID
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Récupérer un utilisateur par email
    @Override
    public User getUserByEmail(String email) {
        return (User) userRepository.findByEmail(email).orElse(null);
    }

    // Vérifier si un utilisateur est connecté
    @Override
    public boolean checkIfUserIsConnected(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() && user.get().isConnected();
    }

    // Authentifier un utilisateur par email et mot de passe
    @Override
    public User authenticateUser(String email, String password) {
        User user = (User) userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getPassword().equals(password)) {
            user.setConnected(true);
            userRepository.save(user); // Mettre à jour l'état de la connexion
            return user;
        }
        return null; // Authentification échouée
    }

    // Déconnecter un utilisateur
    @Override
    public User logoutUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setConnected(false);
            return userRepository.save(u); // Mettre à jour l'état de la connexion
        }
        return null; // Utilisateur non trouvé
    }

    // Récupérer tous les utilisateurs
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Assigner un rôle à un utilisateur
    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User u = user.get();
            // Assigner un rôle basé sur le rôleId (supposant que vous avez un service pour cela)
            // u.setRole(roleService.getRoleById(roleId));
            return userRepository.save(u);
        }
        return null; // L'utilisateur n'existe pas
    }

    public User updateUser(User user) {
        return null;
    }
}
