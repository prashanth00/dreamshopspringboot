package com.wipro.dream_shops.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryName(String category);

	List<Product> findByBrand(String brand);

	List<Product> findByCategoryAndBrand(String category, String brand);

	List<Product> findByName(String name);

	List<Product> findByBrandAndName(String brand, String name);

	Long countByBrandAndName(String brand, String name);
	
}
