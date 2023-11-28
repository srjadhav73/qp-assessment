package com.grocery.assignment.service;

import java.util.List;

import com.grocery.assignment.dtos.UserDto;

public interface UserService {

	 UserDto createUser(UserDto user);
	 UserDto updateUser(UserDto user,Integer userId);
	 UserDto getUserById(Integer userId);
	 List<UserDto> getAllUsers();
	 
	 void deleteUser(Integer userId);
}
