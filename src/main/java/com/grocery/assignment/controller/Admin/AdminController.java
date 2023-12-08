package com.grocery.assignment.controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.assignment.dtos.OrderDto;
import com.grocery.assignment.dtos.UserDto;
import com.grocery.assignment.service.AdminOrder.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<OrderDto>> getAllOrders()
	{
		return ResponseEntity.ok(this.adminService.getAllPlacedOrder());
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/assignRole")
	public ResponseEntity<UserDto> assignRole(@RequestBody UserDto userDto){
		UserDto assignRoleToUser = this.adminService.assignRoleToUser(userDto);
		return new ResponseEntity<>(assignRoleToUser,HttpStatus.CREATED);
	}
}
