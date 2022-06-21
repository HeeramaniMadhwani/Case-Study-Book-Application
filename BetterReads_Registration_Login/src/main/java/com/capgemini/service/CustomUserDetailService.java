package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.model.CustomUserDetails;
import com.capgemini.model.User;
import com.capgemini.repository.UserRepository;

@Service 
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = this.uRepo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("No User Found");
		}
		return new CustomUserDetails(user);
	}

}
