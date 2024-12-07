package com.wipro.dream_shops.service.cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.Cart;
import com.wipro.dream_shops.model.CartItem;
import com.wipro.dream_shops.model.Product;
import com.wipro.dream_shops.repository.CartItemRepository;
import com.wipro.dream_shops.repository.CartRepository;
import com.wipro.dream_shops.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{
	
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final IProductService productService;
	private final CartService cartService;

	@Override
	public void addItemToCart(Long cartId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		//1. Get the cart
		//2. Get the product
		//3. check if the product in the cart
		//4. If yes,then increase the quantity with the requested quantity
		//5. If no, then initiate a new cartItem entry.
		Cart cart=cartService.getCart(cartId);
		Product product = productService.getProductById(productId);
		CartItem cartItem=cart.getItems()
				.stream()
				.filter(item->item.getProduct().getId().equals(productId))
				.findFirst().orElse(new CartItem());
		if(cartItem.getId()==null) {
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setUnitPrice(product.getPrice());
		}
		else {
			cartItem.setQuantity(cartItem.getQuantity()+quantity);
		}
		cartItem.setTotalPrice();
		cart.addItem(cartItem);
		cartItemRepository.save(cartItem);
		cartRepository.save(cart);
	}

	@Override
	public void removeItemFromCart(Long cartId, Long productId) {
		// TODO Auto-generated method stub
		Cart cart=cartService.getCart(cartId);
		CartItem itemToRemove=getCartItem(cartId, productId);
		cart.removeItem(itemToRemove);
		cartRepository.save(cart);
	}

	@Override
	public void updateItemQuantity(Long cartId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		Cart cart=cartService.getCart(cartId);
		cart.getItems()
			.stream()
			.filter(item->item.getProduct().getId().equals(productId))
			.findFirst()
			.ifPresent(item->{
			item.setQuantity(quantity);
			item.setUnitPrice(item.getProduct().getPrice());	
			item.setTotalPrice();
		});
		BigDecimal totalAmount=cart.getItems()
				.stream().map(CartItem :: getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		cart.setTotalAmount(totalAmount);
		cartRepository.save(cart);
	}
	
	@Override
	public CartItem getCartItem(Long cartId,Long productId) {
		Cart cart=cartService.getCart(cartId);
		return cart.getItems()
				.stream()
				.filter(item->item.getProduct().getId().equals(productId))
				.findFirst().orElseThrow(()->new ResourceNotFoundException("Product not found"));
	}

}
