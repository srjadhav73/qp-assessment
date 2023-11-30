package com.grocery.assignment.service;

import java.util.List;

import com.grocery.assignment.dtos.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto); 
	CategoryDto updateCategory(CategoryDto categoryDto,Integer cId);
	CategoryDto getCategoryById(Integer cId);
	
	List<CategoryDto> getAllCategory();
	void deleteCategory(Integer cId);
}
