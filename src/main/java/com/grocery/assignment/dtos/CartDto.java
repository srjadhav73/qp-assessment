package com.grocery.assignment.dtos;


import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartDto {

	private int id;
	@NotNull
	private int groceryId;
	@NotNull
	private int quantity;
	private double totalPrice;
	private Date createdDate;
	private int userId;
	private String productName;
}
