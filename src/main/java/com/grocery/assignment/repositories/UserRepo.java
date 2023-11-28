package com.grocery.assignment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.assignment.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
