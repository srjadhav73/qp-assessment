package com.grocery.assignment.serviceImpl.AdminOrder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grocery.assignment.dtos.OrderDto;
import com.grocery.assignment.dtos.UserDto;
import com.grocery.assignment.entity.Order;
import com.grocery.assignment.entity.OrderStatus;
import com.grocery.assignment.entity.Role;
import com.grocery.assignment.entity.User;
import com.grocery.assignment.repositories.OrderRepository;
import com.grocery.assignment.repositories.RoleRepository;
import com.grocery.assignment.repositories.UserRepo;
import com.grocery.assignment.service.AdminOrder.AdminService;

@Component
public class AdminServiceImpl implements AdminService{

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<OrderDto> getAllPlacedOrder(){
		
		List<Order> orderList=orderRepo.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Pending));
		return orderList.stream().map((Order o) -> o.getOrderToDto(o)).collect(Collectors.toList());
		
	}
	
	public UserDto assignRoleToUser(UserDto userDto)
	{
		User user = this.userRepo.findById(userDto.getId()).orElseThrow(() -> new NullPointerException("User not found"));
		
		HashSet<Role> findByRole = this.roleRepo.findRoleById(userDto.getRoleId());
		user.setRoles(findByRole);
		User saveUser = this.userRepo.save(user);
		
		return user.getDto(saveUser);
		
	}
}
