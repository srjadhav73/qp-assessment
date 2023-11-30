package com.grocery.assignment.dtos;

import com.grocery.assignment.entity.Category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GroceryDto {

	private int id;
	
	@NotEmpty(message = "Grocery name can not be blank.")
	private String groceryName;
	
	@NotEmpty(message = "Grocery description can not be blank.")
	private String groceryDesc;
	
	@NotNull(message = "Grocery price can not be blank.")
	private Double price;
	
	@NotNull(message = "Grocery quantity can not be blank.")
	private int groceryQty;
	
	private Boolean stock;
	
	
	private Category category;
}
