package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    List<Produit> findByNomContainingIgnoreCase(String name);

    Produit findByCategorie(Categorie c);
}
