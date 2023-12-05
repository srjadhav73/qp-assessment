package com.grocery.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.assignment.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	Cart findByUserId(int id);
}
