package com.wipro.dream_shops.service.order;

import java.util.List;

import com.wipro.dream_shops.dto.OrderDto;
import com.wipro.dream_shops.model.Order;

public interface IOrderService {
	Order placeOrder(Long userId);
	OrderDto getOrder(Long orderId);
	List<OrderDto> getUserOrders(Long userId);
	OrderDto convertToDto(Order order);
}
