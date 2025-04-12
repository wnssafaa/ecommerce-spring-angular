package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByLib(String lib);
}
