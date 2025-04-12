package com.example.Ecommerce.Entities;

import jakarta.persistence.*;

@Entity
public class LignePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "panier_id") // Nom de la colonne pour la clé étrangère vers Panier
    private Panier panier; // <-- Champ obligatoire pour la relation "mappedBy"

    @ManyToOne // Relation Many-to-One avec Produit
    @JoinColumn(name = "produit_id") // Clé étrangère vers Produit
    private Produit produit;

    private int quantite;

    public LignePanier(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public LignePanier() {

    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPanier(Panier panier) {
    }
}
