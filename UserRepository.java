package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<Object> findByEmail(String email);
}
