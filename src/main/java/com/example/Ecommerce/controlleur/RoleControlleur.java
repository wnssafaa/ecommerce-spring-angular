package com.example.Ecommerce.controlleur;

import com.example.Ecommerce.Entities.Role;
import com.example.Ecommerce.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleControlleur {

    @Autowired
    private RoleService service;

    // ➕ Ajouter un rôle
    @PostMapping("/add")
    public Role addRole(@RequestBody Role role) {
        return service.addRole(role);
    }

    // 🔄 Modifier un rôle
    @PutMapping("/update")
    public Role updateRole(@RequestBody Role role) {
        return service.updateRole(role);
    }

    // ❌ Supprimer un rôle par ID
    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable long id) {
        return service.deleteRole(id);
    }

    // 📄 Lister tous les rôles
    @GetMapping("/list")
    public List<Role> getAllRoles() {
        return service.getAllRoles();
    }

    // 🔍 Obtenir un rôle par ID
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable long id) {
        return service.getRoleById(id);
    }
}
