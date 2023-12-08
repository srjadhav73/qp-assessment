package com.grocery.assignment.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.grocery.assignment.entity.OrderStatus;

import lombok.Data;


@Data
public class OrderDto {

private int id;
	
	private String orderDescription;
	private Date orderDate;
	private String payment;
	private OrderStatus orderStatus;
	private double totalAmount;
	private UUID trackingId;
	private String userName;
	private String address;
	private List<CartDto> cart;
}
