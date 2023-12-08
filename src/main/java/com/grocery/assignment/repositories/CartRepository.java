package com.grocery.assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.assignment.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	Cart findByUserIdAndGroceryId(int id,int groceryId);
	List<Cart>findByUserId(int id);
}
