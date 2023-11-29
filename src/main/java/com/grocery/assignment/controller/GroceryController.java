package com.grocery.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.assignment.common.ApiResponse;
import com.grocery.assignment.dtos.GroceryDto;
import com.grocery.assignment.service.GroceryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {

	@Autowired
	GroceryService groceryService;
	
    //GET - All Grocery
	@PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
	@GetMapping("/")
	public ResponseEntity<List<GroceryDto>> getAllGrocery(){
		return ResponseEntity.ok(groceryService.getAllGrocery());
	}
	
    //POST - Add Product
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addNewGrocery")
	public ResponseEntity<GroceryDto> addNewGrocery(@Valid @RequestBody GroceryDto groceryDto){
		GroceryDto addNewGrocery = this.groceryService.addNewGrocery(groceryDto);
		return new ResponseEntity<>(addNewGrocery,HttpStatus.CREATED);
	}
	
	//PUT - Update Grocery
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{groceryId}")
	public ResponseEntity<GroceryDto> update(@Valid @RequestBody GroceryDto groceryDto,@PathVariable("groceryId") Integer gId){
		GroceryDto updateGroceryDto = this.groceryService.updateGrocery(groceryDto, gId);
		return  ResponseEntity.ok(updateGroceryDto);
		
	}
	
	//Get - Grocery By Id
	@PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
	@GetMapping("/{gId}")
	public ResponseEntity<GroceryDto> getGrocery(@PathVariable Integer gId){
		return ResponseEntity.ok(this.groceryService.getGroceryById(gId));
	}
	
	//DELETE - Grocery delete by Id
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{gId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer gId)
	{
		this.groceryService.deleteGrocery(gId);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("Grocery deleted successfully..!!",true),HttpStatus.OK);
	}
	
}
