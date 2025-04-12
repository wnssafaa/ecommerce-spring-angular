package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    List<Commande> findByUtilisateurId(Long userId);
}
