package com.wipro.dream_shops.service.order;

import com.wipro.dream_shops.model.Order;

public interface IOrderService {
	Order placeOrder(Long userId);
	Order getOrder(Long orderId);
}
