package com.grocery.assignment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.assignment.common.ApiResponse;
import com.grocery.assignment.dtos.UserDto;
import com.grocery.assignment.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
      
	  @Autowired
	  private UserService userService;
	 
	 //POST - create user
	
	 @PostMapping("/createUser")
	 public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	 {
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		 
	 }
     //PUT- update user
	 @PutMapping("/admin/updateUser/{userId}")
	 public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uId)
	 {
		 UserDto updatedUserDto = this.userService.updateUser(userDto,uId);
		 return  ResponseEntity.ok(updatedUserDto);
	 }
	 //DELETE - Delete user
	 @DeleteMapping("/admin/deleteUser/{userId}")
     public ResponseEntity<ApiResponse>  deleteUser(@PathVariable("userId") Integer uId)
     {
    	 this.userService.deleteUser(uId);
    	 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully..!!",true),HttpStatus.OK);
     }
	 
	 //GET - get All user
	 @GetMapping("/admin/")
	 public ResponseEntity<List<UserDto>> getAllUsers()
	 {
		 return ResponseEntity.ok(this.userService.getAllUsers());
	 }
	 
	 //GET - Single User
	 
	 @GetMapping("/getSingleUsers/{userId}")
	 public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId)
	 {
		 return ResponseEntity.ok(this.userService.getUserById(userId));
	 }
}
