package com.wipro.dream_shops.service.cart;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.Cart;
import com.wipro.dream_shops.model.CartItem;
import com.wipro.dream_shops.repository.CartItemRepository;
import com.wipro.dream_shops.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final AtomicLong cartIdGenerator=new AtomicLong(0);
	
	@Override
	public Cart getCart(Long id) {
		// TODO Auto-generated method stub
		Cart cart=cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
		BigDecimal totalAmount=cart.getTotalAmount();
		cart.setTotalAmount(totalAmount);
		return cartRepository.save(cart);
	}

	@Override
	public void clearCart(Long id) {
		// TODO Auto-generated method stub
		Cart cart=getCart(id);
		cartItemRepository.deleteAllByCartId(id);
		cart.getItems().clear();
		cartRepository.deleteById(id);
	}

	@Override
	public BigDecimal getTotalPrice(Long id) {
		// TODO Auto-generated method stub
		Cart cart=getCart(id);
		return cart.getTotalAmount();
	}
	
	public Long initializeNewCart() {
		Cart newCart=new Cart();
		Long newCartId=cartIdGenerator.incrementAndGet();
		newCart.setId(newCartId);
		return cartRepository.save(newCart).getId();
	}

}
