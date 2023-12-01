package com.grocery.assignment.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.assignment.dtos.CategoryDto;
import com.grocery.assignment.dtos.GroceryDto;
import com.grocery.assignment.entity.Category;
import com.grocery.assignment.entity.Grocery;
import com.grocery.assignment.exceptions.ResourceNotFoundException;
import com.grocery.assignment.repositories.CategoryRepo;
import com.grocery.assignment.repositories.GroceryRepo;
import com.grocery.assignment.service.GroceryService;

@Service
public class GroceryServiceImpl implements GroceryService{

	@Autowired
	private GroceryRepo groceryRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	//Add new Grocery Item
	@Override
	public GroceryDto addNewGrocery(GroceryDto groceryDto) {
		
		//Check category is exist or not
		Category category = this.categoryRepo.findById(groceryDto.getCategory().getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", groceryDto.getCategory().getCategoryId()));
		Grocery grocery=this.dtoToGrocery(groceryDto);
		
		//Set to category
		Grocery savedGrocery = this.groceryRepo.save(grocery);
		
		
		CategoryDto catDto=new CategoryDto();
		
		catDto.setCategoryId(category.getCategoryId());
		catDto.setCategoryName(category.getCategoryName());
		savedGrocery.setCategory(this.categoryServiceImpl.dtoToCategory(catDto));
		return this.groceryToDto(savedGrocery);
	}

	//Update existing grocery item
	@Override
	public GroceryDto updateGrocery(GroceryDto groceryDto, Integer gId) {
		
		Grocery grocery = this.groceryRepo.findById(gId).orElseThrow(() -> new ResourceNotFoundException("Grocery","id",gId));
		
		grocery.setGroceryName(groceryDto.getGroceryName());
		grocery.setGroceryQty(groceryDto.getGroceryQty());
		grocery.setPrice(groceryDto.getPrice());
		grocery.setStock(groceryDto.getStock());
		grocery.setCategory(groceryDto.getCategory());
		Grocery updatedGrocery = this.groceryRepo.save(grocery);
		return this.groceryToDto(updatedGrocery);
	}

	//Get grocery item by id
	@Override
	public GroceryDto getGroceryById(Integer gId) {
		Grocery grocery = this.groceryRepo.findById(gId)
						.orElseThrow(() -> new ResourceNotFoundException("Grocery", "id", gId));
		return this.groceryToDto(grocery);
	}

	//Get all grocery item
	@Override
	public List<GroceryDto> getAllGrocery() {
		List<Grocery> groceries = this.groceryRepo.findAll();
		
		List<GroceryDto> allGroceries = groceries.stream()
				 .map(grocery -> this.groceryToDto(grocery))
				 .collect(Collectors.toList());
		
		return allGroceries;
	}

	//Delete grocery item by id
	@Override
	public void deleteGrocery(Integer gId) {
		
		Grocery grocery = this.groceryRepo.findById(gId)
						.orElseThrow(() -> new ResourceNotFoundException("Grocery", "id", gId));
		
		this.groceryRepo.delete(grocery);
	}
	
	
	
	private Grocery dtoToGrocery(GroceryDto groceryDto)
	{
		Grocery grocery=this.modelMapper.map(groceryDto,Grocery.class);
		return grocery;
	}
	
	private GroceryDto groceryToDto(Grocery grocery)
	{
		GroceryDto groceryDto=this.modelMapper.map(grocery, GroceryDto.class);
		return groceryDto;
	}

}
