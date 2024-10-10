package com.wipro.dream_shops.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.model.Category;
import com.wipro.dream_shops.model.Product;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findByName(String name);
	
	boolean existsByName(String name);
}
