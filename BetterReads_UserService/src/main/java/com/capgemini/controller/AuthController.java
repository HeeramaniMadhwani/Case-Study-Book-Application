package com.capgemini.controller;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController 
{ 
	    @Autowired
        private AuthenticationManager authenticationManager;
        
	    @PostMapping("/signin")
	    public ResponseEntity<String> authenticateUser(@RequestBody User user)
	    {
	    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	    			                                                               user.getEmail(),user.getPassword()));
	    	
	    	SecurityContextHolder.getContext().setAuthentication(authentication);
	    	return new ResponseEntity<String>("User signed in successfully",HttpStatus.OK);
	    }
}