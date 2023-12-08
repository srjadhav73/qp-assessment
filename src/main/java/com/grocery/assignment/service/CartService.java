package com.grocery.assignment.service;

import java.util.List;

import com.grocery.assignment.dtos.CartDto;
import com.grocery.assignment.dtos.OrderDto;
import com.grocery.assignment.dtos.PlaceOrderDto;

public interface CartService {

	CartDto addCartDto(CartDto cartDto);
	OrderDto getCartByUserId (int userId);
	OrderDto placeOrder(PlaceOrderDto placeOrderDto);
	public List<OrderDto> getMyPlaceOrder(int UserId);
}
