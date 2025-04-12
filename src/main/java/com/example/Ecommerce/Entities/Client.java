package com.example.Ecommerce.Entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String prenom;
    private int age;
    private Date dateNaissance;
    private String email;
    private String password;
    private boolean isConnected;

    @ManyToOne
    @JoinColumn(name = "id_Role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "id_Panier")
    private Panier panier;

    // Constructeurs
    public Client() {
    }

    public Client(String name, String prenom, int age, Date dateNaissance, String email, String password, Role role) {
        this.name = name;
        this.prenom = prenom;
        this.age = age;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", dateNaissance=" + dateNaissance +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isConnected=" + isConnected +
                ", role=" + role +
                '}';
    }
}
