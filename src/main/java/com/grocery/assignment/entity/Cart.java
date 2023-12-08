package com.grocery.assignment.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="grocery_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Grocery grocery;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="order_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Order order;
	
	private double totalPrice;
	private int quantity;
}
