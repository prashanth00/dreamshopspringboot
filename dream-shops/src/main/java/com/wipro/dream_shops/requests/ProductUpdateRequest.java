package com.wipro.dream_shops.requests;

import java.math.BigDecimal;

import com.wipro.dream_shops.model.Category;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductUpdateRequest {
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String description;
	private Category category;

}
