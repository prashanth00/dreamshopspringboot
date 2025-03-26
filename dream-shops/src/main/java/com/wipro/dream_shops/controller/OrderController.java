package com.wipro.dream_shops.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dream_shops.dto.OrderDto;
import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.Order;
import com.wipro.dream_shops.response.ApiResponse;
import com.wipro.dream_shops.service.order.IOrderService;
import com.wipro.dream_shops.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
	private final IOrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId){
		try {
			Order order=orderService.placeOrder(userId);
			OrderDto orderDto=orderService.convertToDto(order);
			return ResponseEntity.ok(new ApiResponse("Item Order Success!", orderDto));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error occurred", e.getMessage()));
		}
	}
	
	@GetMapping("/{orderId}/order")
	public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId){
		try {
			OrderDto order=orderService.getOrder(orderId);
			return ResponseEntity.ok(new ApiResponse("Item Order Success!", order));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Oops!", e.getMessage()));
		}
	}
	
	@GetMapping("/{userId}/order")
	public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId){
		try {
			List<OrderDto> order=orderService.getUserOrders(userId);
			return ResponseEntity.ok(new ApiResponse("Item Order Success!", order));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Oops!", e.getMessage()));
		}
	}
}
