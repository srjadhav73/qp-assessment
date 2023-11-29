package com.grocery.assignment.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.grocery.assignment.entity.User;

@Component
public class AuthorityHelper {

  public boolean isCurrentLoggedInUserId(Authentication authentication, String userId) {
	  User u = (User) authentication.getPrincipal();
	  return userId.equalsIgnoreCase(String.valueOf(u.getId()));
  }
}