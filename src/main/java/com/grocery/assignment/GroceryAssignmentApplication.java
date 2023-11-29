package com.grocery.assignment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.qos.logback.core.model.Model;

@SpringBootApplication
public class GroceryAssignmentApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(GroceryAssignmentApplication.class, args);
	}
    
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
