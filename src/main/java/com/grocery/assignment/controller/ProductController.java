package com.grocery.assignment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	
	@RequestMapping()
	public String addProduct()
	{
		return "add product";
	}
}
