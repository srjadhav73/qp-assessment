package com.grocery.assignment.dtos;

import lombok.Data;

@Data
public class PlaceOrderDto {

	private int userId;
	private String address;
	private String orderDescription;
}
