package com.example.Ecommerce.Entities;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id // <-- Clé primaire obligatoire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation

    int id;
    @OneToOne
    @JoinColumn(name = "id_Role")
    private Role role;

    public Admin(int id, Role role) {
        this.id = id;
        this.role = role;
    }
    public Admin( Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
