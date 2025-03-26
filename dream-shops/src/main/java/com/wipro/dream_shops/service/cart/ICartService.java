package com.wipro.dream_shops.service.cart;

import java.math.BigDecimal;

import com.wipro.dream_shops.model.Cart;
import com.wipro.dream_shops.model.User;

public interface ICartService {
	Cart getCart(Long id);
	void clearCart(Long id);
	BigDecimal getTotalPrice(Long id);
	Cart getCartByUserId(Long userId);
	Cart initializeNewCart(User user);
}
