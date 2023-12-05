package com.grocery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.assignment.dtos.CartDto;
import com.grocery.assignment.security.CustomUserDetailsService;
import com.grocery.assignment.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	//Post cart API
	@PreAuthorize("hasRole('NORMAL')")
	@PostMapping("/addToCart")
	public ResponseEntity<CartDto> addToCart(@RequestBody CartDto cartDto) throws Exception{
		 
		//Get  logged Current user 
	//	this.customUserDetailsService.loadUserByUsername();
		
		CartDto savedCartDto = this.cartService.addCartDto(cartDto);
		return new ResponseEntity<>(savedCartDto,HttpStatus.CREATED);
		
	}
}
