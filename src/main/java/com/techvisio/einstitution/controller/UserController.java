package com.techvisio.einstitution.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/", method = RequestMethod.GET)
	public User getLoggedInUser(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return user;
	}
	
}
