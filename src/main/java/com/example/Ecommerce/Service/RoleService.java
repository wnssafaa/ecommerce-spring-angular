package com.example.Ecommerce.Service;

import com.example.Ecommerce.Entities.Role;
import com.example.Ecommerce.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleServiceImp {

    @Autowired
    private final RoleRepository repo;

    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }

    @Override
    public Role addRole(Role role) {
        return repo.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        Optional<Role> existingRole = repo.findById(role.getId());
        if (existingRole.isPresent()) {
            return repo.save(role);
        } else {
            throw new RuntimeException("Role not found with id: " + role.getId());
        }
    }

    @Override
    public String deleteRole(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Role deleted successfully";
        } else {
            return "Role not found";
        }
    }

    @Override
    public Role getRoleById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Role getRoleByLib(String lib) {
        return repo.findByLib(lib);
    }

    @Override
    public List<Role> getAllRoles() {
        return repo.findAll();
    }
}
