package com.grocery.assignment.service;

import java.util.List;

import com.grocery.assignment.dtos.GroceryDto;

public interface GroceryService {

	GroceryDto addNewGrocery(GroceryDto groceryDto,Integer cId);
	GroceryDto updateGrocery(GroceryDto groceryDto,Integer gId);
	GroceryDto getGroceryById(Integer gId);
	List<GroceryDto> getAllGrocery();
	
	void deleteGrocery(Integer gId);
	
}
