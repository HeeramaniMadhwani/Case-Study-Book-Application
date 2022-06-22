package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.helper.JwtUtil;
import com.capgemini.model.JwtRequest;
import com.capgemini.model.JwtResponse;
import com.capgemini.service.CustomUserDetailService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value="/api/v1/token", method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest+" Error");
		try {
			System.out.println("Testing..");
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		    System.out.println("Done!!");
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Invalid Credentials");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Invalid Credentials");
		}
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT: " +token);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}
