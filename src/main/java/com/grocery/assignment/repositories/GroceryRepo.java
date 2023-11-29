package com.grocery.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.assignment.entity.Grocery;

public interface GroceryRepo extends JpaRepository<Grocery,Integer>{

}
