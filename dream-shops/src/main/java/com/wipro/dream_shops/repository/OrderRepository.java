package com.wipro.dream_shops.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.dto.OrderDto;
import com.wipro.dream_shops.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findByUserId(Long userId);

}
