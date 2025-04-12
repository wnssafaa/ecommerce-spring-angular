package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Entities.Produit;

import java.util.List;

public interface CategoryServiceImp {
    public String addCategory(Categorie C);
    public String DeleteCategory(int id);
    public String updateCategory(Categorie c);
    public Categorie CategoryById(int id);
    public List<Categorie> allCategory();
    public Categorie CategoryByName(String name);
    public List<Produit> ProductsByCategory(int categoryId);

}
