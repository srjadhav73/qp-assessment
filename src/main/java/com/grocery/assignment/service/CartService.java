package com.grocery.assignment.service;

import com.grocery.assignment.dtos.CartDto;
import com.grocery.assignment.entity.ItemRequest;

public interface CartService {

	CartDto addCartDto(CartDto cartDto) throws Exception;
}
