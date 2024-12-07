package com.wipro.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.dream_shops.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
