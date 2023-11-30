package com.grocery.assignment.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.assignment.dtos.CategoryDto;
import com.grocery.assignment.entity.Category;
import com.grocery.assignment.exceptions.ResourceNotFoundException;
import com.grocery.assignment.repositories.CategoryRepo;
import com.grocery.assignment.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category dtoToCategory = this.dtoToCategory(categoryDto);
		Category saveCategory = this.categoryRepo.save(dtoToCategory);
		return this.categoryToDto(saveCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer cId) {
		
		Category category = this.categoryRepo
								.findById(cId)
								.orElseThrow(() -> new ResourceNotFoundException("Category", "id", cId));
		
		category.setCategoryName(categoryDto.getCategoryName());
		Category updatedCategory = this.categoryRepo.save(category);
		return this.categoryToDto(updatedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer cId) {
		Category category = this.categoryRepo
				.findById(cId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", cId));

		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allCategoryList = this.categoryRepo.findAll();
		List<CategoryDto> getAllCategory = allCategoryList.stream()
				   .map(category -> this.categoryToDto(category))
				   .collect(Collectors.toList());
		
		return getAllCategory;
	}

	@Override
	public void deleteCategory(Integer cId) {
		Category category = this.categoryRepo
				.findById(cId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", cId));
		this.categoryRepo.delete(category);
	}
    
	public CategoryDto categoryToDto(Category category)
	{
		CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
		return categoryDto;
	}
	
	public Category dtoToCategory(CategoryDto categoryDto)
	{
		Category category = this.modelMapper.map(categoryDto,Category.class);
		return category;
	}
}
