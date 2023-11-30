package com.grocery.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Grocery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="grocery_name")
	private String groceryName;
	
	@Column(name="grocery_desc")
	private String groceryDesc;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="grocery_qty")
	private int groceryQty;
	
	@Column(name="stock")
	private Boolean stock;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
}
