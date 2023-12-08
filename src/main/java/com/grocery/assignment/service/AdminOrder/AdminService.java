package com.grocery.assignment.service.AdminOrder;

import java.util.List;

import com.grocery.assignment.dtos.OrderDto;
import com.grocery.assignment.dtos.UserDto;

public interface AdminService {

	public List<OrderDto> getAllPlacedOrder();
	public UserDto assignRoleToUser(UserDto userDto);
}
