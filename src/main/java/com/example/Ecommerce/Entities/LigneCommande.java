package com.example.Ecommerce.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LigneCommande {
    @Id
    @GeneratedValue
    private Long id;
    private int quantite;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Commande commande;

    public LigneCommande() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public void setProduit(Produit produit) {
    }

    public LigneCommande(Long id, int quantite, Produit produit, Commande commande) {
        this.id = id;
        this.quantite = quantite;
        this.produit = produit;
        this.commande = commande;
    }
}
