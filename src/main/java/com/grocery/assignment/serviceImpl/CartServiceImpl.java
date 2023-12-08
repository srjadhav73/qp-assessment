package com.grocery.assignment.serviceImpl;



import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.assignment.dtos.CartDto;
import com.grocery.assignment.dtos.OrderDto;
import com.grocery.assignment.dtos.PlaceOrderDto;
import com.grocery.assignment.entity.Cart;
import com.grocery.assignment.entity.Grocery;
import com.grocery.assignment.entity.Order;
import com.grocery.assignment.entity.OrderStatus;
import com.grocery.assignment.entity.User;
import com.grocery.assignment.exceptions.ResourceNotFoundException;
import com.grocery.assignment.repositories.CartRepository;
import com.grocery.assignment.repositories.GroceryRepo;
import com.grocery.assignment.repositories.OrderRepository;
import com.grocery.assignment.repositories.UserRepo;
import com.grocery.assignment.security.CustomUserDetailsService;
import com.grocery.assignment.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private GroceryRepo groceryRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CustomUserDetailsService customUser;
	
	@Override
	public CartDto addCartDto(CartDto cartDto) {
		
		User user 		= this.userRepo.findByEmail(customUser.currentUser())
	             		.orElseThrow(() -> new ResourceNotFoundException("Username ", "id", Integer.parseInt(customUser.currentUser())));
		Order activeOrder = this.orderRepo.findByUserIdAndOrderStatus(user.getId(),OrderStatus.Pending);
		Grocery grocery = this.groceryRepo.findById(cartDto.getGroceryId())
				.orElseThrow(() -> new ResourceNotFoundException("Grocery", "id", cartDto.getGroceryId()));
		Cart cart = this.cartRepo.findByUserIdAndGroceryId(user.getId(),cartDto.getGroceryId());
		Order order=new Order();
		Cart cart1=new Cart();
		
		if(!grocery.getStock()){
	    	throw new ResourceNotFoundException("Grocery out of stock..!", "", 0);
	    }
        if(activeOrder!=null)
        {
        	activeOrder.setTotalAmount(activeOrder.getTotalAmount()+(grocery.getPrice()*cartDto.getQuantity())+cart.getTotalPrice());
            activeOrder.getCart().add(cart);
            this.orderRepo.save(activeOrder);
            cart1.setOrder(activeOrder);
        }else
        {
    		order.setTotalAmount(grocery.getPrice()*cartDto.getQuantity());
    		order.setUser(user);
    		order.setOrderStatus(OrderStatus.Pending);
    		orderRepo.save(order);
    		cart1.setOrder(order);
        }
	    
	    if(cart!=null)
	    {
	    	if(cart.getGrocery().getId()==grocery.getId())
	        {
	    		cart1.setId(cart.getId());
	    		cart1.setQuantity(cart.getQuantity() + cartDto.getQuantity());
	    		cart1.setTotalPrice((cart.getTotalPrice())+(cartDto.getQuantity()*grocery.getPrice()));
	        }
	    }else
	    {
	    	cart1.setQuantity(cartDto.getQuantity());
	    	cart1.setTotalPrice(cartDto.getQuantity()*grocery.getPrice());
	    }
	    cart1.setGrocery(grocery);
	    cart1.setUser(user);
	    cart1.setCreatedDate(new Date());
        Cart addToCart = this.cartRepo.save(cart1);
		return this.cartToDto(addToCart);
	}
    
	//Get Cart grocery
	@Override
	public OrderDto getCartByUserId(int userId) {
		Order activeOrder = this.orderRepo.findByUserIdAndOrderStatus(userId,OrderStatus.Pending);
		
		if(activeOrder!=null)
		{
			List<CartDto> cartItems = activeOrder.getCart().stream().map(cart -> this.cartToDto(cart)).collect(Collectors.toList());
		    
			OrderDto orderDto= new OrderDto();
			orderDto.setTotalAmount(activeOrder.getTotalAmount());
			orderDto.setId(activeOrder.getId());
			orderDto.setOrderStatus(activeOrder.getOrderStatus());
			orderDto.setCart(cartItems);
			return orderDto;
		}else
		{
			 throw new NullPointerException("Grocery not available in cart..!");
		}
	    
	}
	
	public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
		Order activeOrder = this.orderRepo.findByUserIdAndOrderStatus(placeOrderDto.getUserId(),OrderStatus.Pending);
		
		if(activeOrder!=null)
		{
			Optional<User> user = userRepo.findById(placeOrderDto.getUserId());
			if(user.isPresent()) {
				activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
				activeOrder.setAddress(placeOrderDto.getAddress());
				activeOrder.setOrderDate(new Date());
				activeOrder.setOrderStatus(OrderStatus.Placed);
				activeOrder.setTrackingId(UUID.randomUUID());
				orderRepo.save(activeOrder);
				
				return activeOrder.getOrderToDto(activeOrder);
			}
		}else
		{
			 throw new NullPointerException("No order pending..!");
		}
		return null;	
	}
	
	public List<OrderDto> getMyPlaceOrder(int UserId)
	{
		return this.orderRepo.findByUserIdAndOrderStatusIn(UserId, List.of(OrderStatus.Placed,OrderStatus.Pending))
		              .stream().map((Order o) -> o.getOrderToDto(o)).collect(Collectors.toList());
	}
	
	
	private Cart dtoToCart(CartDto cartDto)	{
		Cart cart=this.modelMapper.map(cartDto,Cart.class);
		return cart;
	}
	private CartDto cartToDto(Cart cart)
	{
		CartDto cartDto=this.modelMapper.map(cart, CartDto.class);
		return cartDto;
	}

	

	
}
