package com.grocery.assignment.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.assignment.dtos.CartDto;
import com.grocery.assignment.dtos.OrderDto;
import com.grocery.assignment.dtos.PlaceOrderDto;
import com.grocery.assignment.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	//Post cart API
	@PreAuthorize("hasRole('NORMAL')")
	@PostMapping("/addToCart")
	public ResponseEntity<CartDto> addToCart(@RequestBody CartDto cartDto) throws Exception{
		CartDto savedCartDto = this.cartService.addCartDto(cartDto);
		return new ResponseEntity<>(savedCartDto,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/{userId}")
	public ResponseEntity<OrderDto> getAllCartGroceryByUserId(@PathVariable int userId)
	{
		return ResponseEntity.ok(this.cartService.getCartByUserId(userId));
	}
	
	@PreAuthorize("hasRole('NORMAL')")
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto)
	{
	       OrderDto placeOrder = this.cartService.placeOrder(placeOrderDto);
		return new ResponseEntity<>(placeOrder,HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/myOrder/{userId}")
	public ResponseEntity<List<OrderDto>> getMyPlaceOrder(@PathVariable int userId)
	{
		return ResponseEntity.ok(this.cartService.getMyPlaceOrder(userId));
	}
	
}
