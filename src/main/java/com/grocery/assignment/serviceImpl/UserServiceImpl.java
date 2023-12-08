package com.grocery.assignment.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grocery.assignment.dtos.UserDto;
import com.grocery.assignment.entity.Order;
import com.grocery.assignment.entity.OrderStatus;
import com.grocery.assignment.entity.User;
import com.grocery.assignment.exceptions.ResourceNotFoundException;
import com.grocery.assignment.repositories.OrderRepository;
import com.grocery.assignment.repositories.UserRepo;
import com.grocery.assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
	private UserRepo userRepo;
    
    @Autowired
    private OrderRepository orderRepo;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto useDto) {
		useDto.setPassword(passwordEncoder.encode(useDto.getPassword()));
		User user=this.dtoToUser(useDto);
		User savedUser = this.userRepo.save(user);
		
		Order order=new Order();
		
		order.setTotalAmount(0);
		order.setUser(savedUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepo.save(order);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.userRepo.findById(userId)
					  .orElseThrow(() -> new ResourceNotFoundException("User"," id ",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId)
				  .orElseThrow(() -> new ResourceNotFoundException("User"," id ",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUser = this.userRepo.findAll();
		List<UserDto> userDtos = allUser.stream()
										.map(user -> this.userToDto(user))
										.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId)
				  .orElseThrow(() -> new ResourceNotFoundException("User"," id ",userId));
		
		this.userRepo.delete(user);
	}
	
	
	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto,User.class);
		return user;
	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}


}
