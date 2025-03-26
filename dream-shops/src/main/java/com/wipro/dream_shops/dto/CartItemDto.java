package com.wipro.dream_shops.dto;

import java.math.BigDecimal;

import com.wipro.dream_shops.model.Product;

import lombok.Data;

public class CartItemDto {
	private Long itemId;
	private Integer quantity;
	private BigDecimal unitPrice;
	private ProductDto product;
}
