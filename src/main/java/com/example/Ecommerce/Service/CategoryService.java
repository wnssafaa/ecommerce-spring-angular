package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Entities.Produit;
import com.example.Ecommerce.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategorieRepository categoryRepository;

    @Override
    public String addCategory(Categorie c) {
        categoryRepository.save(c);
        return "Category added successfully";
    }


    @Override
    public String DeleteCategory(int id) {
        Optional<Categorie> category = categoryRepository.findById((long) id);
        if (category.isPresent()) {
            categoryRepository.deleteById((long) id);
            return "Category deleted successfully";
        } else {
            return "Category not found";
        }
    }

    @Override
    public String updateCategory(Categorie c) {
        Optional<Categorie> existingCategory = categoryRepository.findById(c.getId());
        if (existingCategory.isPresent()) {
            categoryRepository.save(c); // Update the existing category
            return "Category updated successfully";
        } else {
            return "Category not found";
        }
    }
    @Override
    public Categorie CategoryById(int id) {
        Optional<Categorie> category = categoryRepository.findById((long) id);
        return category.orElse(null); // Return the category if present, else return null
    }

    @Override
    public List<Categorie> allCategory() {
        return categoryRepository.findAll(); // Retrieve all categories from the database
    }

    @Override
    public Categorie CategoryByName(String name) {
        return null;
    }

    @Override
    public List<Produit> ProductsByCategory(int categoryId) {
        return List.of();
    }
}
