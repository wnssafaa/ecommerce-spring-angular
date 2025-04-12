package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
