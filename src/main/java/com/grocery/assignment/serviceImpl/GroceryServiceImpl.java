package com.grocery.assignment.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.assignment.dtos.GroceryDto;
import com.grocery.assignment.entity.Grocery;
import com.grocery.assignment.exceptions.ResourceNotFoundException;
import com.grocery.assignment.repositories.GroceryRepo;
import com.grocery.assignment.service.GroceryService;

@Service
public class GroceryServiceImpl implements GroceryService{

	@Autowired
	GroceryRepo groceryRepo;
	
	@Autowired
    private ModelMapper modelMapper;
	
	//Add new Grocery Item
	@Override
	public GroceryDto addNewGrocery(GroceryDto groceryDto) {
		Grocery product=this.dtoToGrocery(groceryDto);
		Grocery savedProdcut = this.groceryRepo.save(product);
		return this.groceryToDto(savedProdcut);
	}

	//Update existing grocery item
	@Override
	public GroceryDto updateGrocery(GroceryDto groceryDto, Integer gId) {
		
		Grocery grocery = this.groceryRepo.findById(gId).orElseThrow(() -> new ResourceNotFoundException("Grocery","id",gId));
		
		grocery.setGroceryName(groceryDto.getGroceryName());
		grocery.setGroceryQty(groceryDto.getGroceryQty());
		grocery.setPrice(groceryDto.getPrice());
		grocery.setStock(groceryDto.getStock());
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
