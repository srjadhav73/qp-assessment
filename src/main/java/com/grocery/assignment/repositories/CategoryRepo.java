package com.grocery.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.assignment.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
