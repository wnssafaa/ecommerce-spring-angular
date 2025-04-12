package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.Categorie;
import com.example.Ecommerce.Entities.Role;
import java.util.List;

public interface RoleServiceImp {
    // ➕ Ajouter un rôle
    Role addRole(Role role);

    // 🔄 Mettre à jour un rôle
    Role updateRole(Role role);

    // ❌ Supprimer un rôle par ID
    String deleteRole(Long id);

    // 🔍 Trouver un rôle par ID
    Role getRoleById(Long id);

    // 🔍 Trouver un rôle par libellé (ex: "Admin", "Client")
    Role getRoleByLib(String lib);
    public List<Role> getAllRoles();
    // 📜 Lister tous les rôles
}
