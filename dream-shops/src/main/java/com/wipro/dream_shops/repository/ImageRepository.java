package com.wipro.dream_shops.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	List<Image> findByProductId(Long id);
}
