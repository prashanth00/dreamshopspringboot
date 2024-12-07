package com.wipro.dream_shops.service.cart;

import java.math.BigDecimal;

import com.wipro.dream_shops.model.Cart;

public interface ICartService {
	Cart getCart(Long id);
	void clearCart(Long id);
	BigDecimal getTotalPrice(Long id);
}
