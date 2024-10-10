package com.wipro.dream_shops.requests;

import java.math.BigDecimal;

import com.wipro.dream_shops.model.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AddProductRequest {
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String description;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;
}
