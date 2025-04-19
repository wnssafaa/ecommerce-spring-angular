package com.example.Ecommerce.Entities;

import jakarta.persistence.*;

@Entity
public class Produit {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private double prix;
    private int quantite;
    @Column(name = "image_url")
    private String imageUrl;
    public Produit() {

    }
    public Produit( String nom, double prix, int quantite, String imageUrl, Categorie categorie) {

        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.imageUrl = imageUrl;
        this.categorie = categorie;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne
    private Categorie categorie;

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }
}
