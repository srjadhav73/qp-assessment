package com.grocery.assignment.serviceImpl;



import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.grocery.assignment.dtos.CartDto;
import com.grocery.assignment.dtos.CategoryDto;
import com.grocery.assignment.dtos.GroceryDto;
import com.grocery.assignment.entity.Cart;
import com.grocery.assignment.entity.Grocery;
import com.grocery.assignment.entity.User;
import com.grocery.assignment.exceptions.CommonExceptionHandler;
import com.grocery.assignment.exceptions.ResourceNotFoundException;
import com.grocery.assignment.repositories.CartRepository;
import com.grocery.assignment.repositories.UserRepo;
import com.grocery.assignment.security.CustomUserDetailsService;
import com.grocery.assignment.service.CartService;
import com.grocery.assignment.service.GroceryService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	GroceryService groceryService;
	
	@Autowired
	private CustomUserDetailsService customUser;
	
	@Override
	public CartDto addCartDto(CartDto cartDto) throws Exception {
		
		User user 		= this.userRepo.findByEmail(customUser.currentUser())
	             		.orElseThrow(() -> new ResourceNotFoundException("Username ", "id", Integer.parseInt(customUser.currentUser())));
		
		//Get Grocery and Validate if grocery id is valid
		GroceryDto grocery = this.groceryService.getGroceryById(cartDto.getGroceryId());
		Cart cart = this.cartRepo.findByUserId(user.getId());
		
	    if(!grocery.getStock()){
	    	throw new ResourceNotFoundException("Grocery out of stock..!", "", 0);
	    }
        if(cart.getGrocery().getId()==grocery.getId())
        {
        	cart.setQuantity(cart.getQuantity() + cartDto.getQuantity());
        	cart.setTotalPrice((cart.getTotalPrice())+(cartDto.getQuantity()*grocery.getPrice()));
        	cart.setCreatedDate(new Date());
        	cart.setUser(user.getId());
        }else
        {
        	Cart newCart= new Cart();
        	newCart.setUser(user.getId());
        	newCart.setCreatedDate(new Date());
        	newCart.setCreatedDate(new Date());
        	Cart addToCart = this.cartRepo.save(newCart);
        }
	    
	   
		return this.cartToDto(addToCart);
	}

	private Cart dtoToCart(CartDto cartDto)
	{
		Cart cart=this.modelMapper.map(cartDto,Cart.class);
		return cart;
	}
	private CartDto cartToDto(Cart cart)
	{
		CartDto cartDto=this.modelMapper.map(cart, CartDto.class);
		return cartDto;
	}
}
