package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Entities.Produit;

import java.util.List;

public interface ProduitServiceImp {
    public String addProduit(Produit p);
    public String updateProduit(Produit p);
    public String DeleteProduit(long id);
    public List<Produit> Produites();
    public Produit ProduitById(long id);
    public Produit ProduitByCategory(Categorie c);
    public String updateProductQuantity(long id, int newQuantity);
    public boolean checkProductAvailability(long id, int quantity);
    public List<Produit> searchProductByName(String name);
    public String updateProductPrice(long id, double newPrice);
}
