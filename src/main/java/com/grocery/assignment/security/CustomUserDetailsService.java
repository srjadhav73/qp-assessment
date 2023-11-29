package com.grocery.assignment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grocery.assignment.entity.User;
import com.grocery.assignment.exceptions.ResourceNotFoundException;
import com.grocery.assignment.repositories.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from database by username
		User user = this.userRepo.findByEmail(username)
						.orElseThrow(() -> new ResourceNotFoundException("User", "email : "+username, 0));
		return user;
	}

}
