package com.wipro.dream_shops.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
}
