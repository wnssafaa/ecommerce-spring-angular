package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entities.Panier;
import com.example.Ecommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier,Long> {
    Panier findByUtilisateurId(Long userId);

    Panier findByUtilisateur(User user);
}
