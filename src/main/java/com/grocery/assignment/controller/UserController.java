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
import com.grocery.assignment.dtos.UserDto;
import com.grocery.assignment.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
      
	  @Autowired
	  private UserService userService;
	  
	 //GET - get All user
	 @GetMapping("/")
	 @PreAuthorize("hasRole('ADMIN')")
	 public ResponseEntity<List<UserDto>> getAllUsers(){
		 return ResponseEntity.ok(this.userService.getAllUsers());
	 }
		 
	 //POST - create user
	 @PostMapping(value = "/create")
	 public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		 
	 }
	 
     //PUT- update user
	 @PutMapping("/update/{userId}")
	 @PreAuthorize("hasRole('ADMIN')")
	 public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uId){
		 UserDto updatedUserDto = this.userService.updateUser(userDto,uId);
		 return  ResponseEntity.ok(updatedUserDto);
	 }
	 
	 //DELETE - Delete user
	 @DeleteMapping("/delete/{userId}")
	 @PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<ApiResponse>  delete(@PathVariable("userId") Integer uId){
    	 this.userService.deleteUser(uId);
    	 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully..!!",true),HttpStatus.OK);
     }
	 
	 //GET - Single User
	 @GetMapping("/{userId}")
	 @PreAuthorize("@authorityHelper.isCurrentLoggedInUserId(authentication, #userId) or hasRole('ADMIN')")
	 public ResponseEntity<UserDto> getUser(@PathVariable String userId){
		 return ResponseEntity.ok(this.userService.getUserById(Integer.parseInt(userId)));
	 }
	 
}
