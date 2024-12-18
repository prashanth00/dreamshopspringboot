package com.wipro.dream_shops.service.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.dream_shops.enums.OrderStatus;
import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.Cart;
import com.wipro.dream_shops.model.Order;
import com.wipro.dream_shops.model.OrderItem;
import com.wipro.dream_shops.model.Product;
import com.wipro.dream_shops.repository.OrderRepository;
import com.wipro.dream_shops.repository.ProductRepository;
import com.wipro.dream_shops.service.cart.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final CartService cartService;
	@Override
	public Order placeOrder(Long userId) {
		// TODO Auto-generated method stub

		Cart cart=cartService.getCartByUserId(userId);
		Order order=createOrder(cart);
		List<OrderItem> orderItemList=createOrderItems(order,cart);
		order.setOrderItems(new HashSet<>(orderItemList));
		order.setTotalAmount(calculateTotalAmount(orderItemList));
		Order savedOrder=orderRepository.save(order);
		
		cartService.clearCart(cart.getId());
		
		return savedOrder;
	}
	
	private Order createOrder(Cart cart) {
		Order order=new Order();
		//set the user
		order.setOrderStatus(OrderStatus.PENDING);
		order.setOrderDate(LocalDate.now());
		return order;
	}
	
	private List<OrderItem> createOrderItems(Order order,Cart cart){
		return cart.getItems().stream().map(cartItem->{
			Product product=cartItem.getProduct();
			product.setInventory(product.getInventory()-cartItem.getQuantity());
			productRepository.save(product);
			return new OrderItem(order,product,cartItem.getQuantity(),cartItem.getUnitPrice());
		}).toList();
	}
	
	private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
		
				.stream()
				.map(item->item.getPrice()
						.multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add);
 	}

	@Override
	public Order getOrder(Long orderId) {
		// TODO Auto-generated method stub
		return orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order Not Found"));
	}
	
	
	@Override
	public List<Order> getUserOrders(Long userId){
		return orderRepository.findByUserId(userId);
	}
}
