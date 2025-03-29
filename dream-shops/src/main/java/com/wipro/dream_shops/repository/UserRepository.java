package com.wipro.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);

	User findByEmail(String email);
}
