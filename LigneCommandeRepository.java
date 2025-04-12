package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long> {
}
