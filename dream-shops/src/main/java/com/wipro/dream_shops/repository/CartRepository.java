package com.wipro.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.model.Cart;
import com.wipro.dream_shops.model.Category;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findByUserId(Long id);
}
