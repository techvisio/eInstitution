package com.techvisio.einstitution.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.Response;

@RestController
@RequestMapping("/service/user")
public class UserController {

	@RequestMapping(value="/loggedinuser", method = RequestMethod.GET)
	public ResponseEntity<Response> getLoggedInUser(){
		User user =null;
		if(SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() !=null){
		user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		Response response=new Response();
		response.setResponseBody(user);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
}
