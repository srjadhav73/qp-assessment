package com.grocery.assignment.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.assignment.entity.Order;
import com.grocery.assignment.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	Order findByUserIdAndOrderStatus(int id, OrderStatus pending);
	List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
	
	List<Order> findByUserIdAndOrderStatusIn(int id, List<OrderStatus> placed);
}
