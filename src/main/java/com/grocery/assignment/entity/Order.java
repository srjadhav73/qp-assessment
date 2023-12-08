package com.grocery.assignment.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.grocery.assignment.dtos.CartDto;
import com.grocery.assignment.dtos.OrderDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String orderDescription;
	private Date orderDate;
	private String payment;
	private String address;
	private OrderStatus orderStatus;
	private double totalAmount;
	private UUID trackingId;
	@OneToOne(cascade = CascadeType.MERGE)
	//@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
	private List<Cart> cart;
	
	
	public OrderDto getOrderToDto(Order order)	{
		
		OrderDto orderDto=new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setAddress(order.getAddress());
		orderDto.setOrderDate(order.getOrderDate());
		orderDto.setOrderDescription(order.getOrderDescription());
		orderDto.setOrderStatus(order.getOrderStatus());
		orderDto.setTrackingId(order.getTrackingId());
		orderDto.setUserName(order.getUser().getName());
		orderDto.setTotalAmount(order.getTotalAmount());
		orderDto.setPayment(order.getPayment());
		
		return orderDto;
	}
	public Order getOrderDtoToOrder(OrderDto orderDto)
	{
		Order order=new Order();
		order.setId(orderDto.getId());
		order.setAddress(orderDto.getAddress());
		order.setOrderDate(orderDto.getOrderDate());
		order.setOrderDescription(orderDto.getOrderDescription());
		order.setOrderStatus(orderDto.getOrderStatus());
		order.setTrackingId(orderDto.getTrackingId());
		order.setTotalAmount(orderDto.getTotalAmount());
		order.setPayment(orderDto.getPayment());
		return order;
	}
}
