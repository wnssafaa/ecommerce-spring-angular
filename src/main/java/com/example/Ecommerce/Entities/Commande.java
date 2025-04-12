package com.example.Ecommerce.Entities;

import jakarta.persistence.*;

import java.util.List;

import java.time.LocalDate;
@Entity
public class Commande {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dateCommande;

    @ManyToOne
    private User utilisateur;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignes;

    public Commande() {

    }

    public void setDateCommande(LocalDate now) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneCommande> lignes) {
        this.lignes = lignes;
    }

    public Commande(Long id, LocalDate dateCommande, User utilisateur, List<LigneCommande> lignes) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.utilisateur = utilisateur;
        this.lignes = lignes;
    }
}
