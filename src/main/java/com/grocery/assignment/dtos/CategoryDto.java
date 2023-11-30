package com.grocery.assignment.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int categoryId;
    
    @NotEmpty(message = "Category Name can not be empty or blank..!!")
	private String categoryName;
}
