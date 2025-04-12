package com.example.Ecommerce.Entities;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String prenom;
    private int age;
    private Date DateNaissance;
    private String email;
    private String password;
    private boolean isConnected;
    @OneToOne
    @JoinColumn(name = "id_Role")
    private Role role;

    public User (){

    }

    public User(Long id, String name, String prenom, int age, Date dateNaissance, String email, String password, boolean isConnected, Role role) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.age = age;
        DateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.isConnected = isConnected;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
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
}
