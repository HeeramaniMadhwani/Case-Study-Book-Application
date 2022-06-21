package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.exception.PasswordValidationException;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;
import com.capgemini.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImplementation(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public UserServiceImplementation() {
		super();
	}
	
	public User Registration(User user) throws UserAlreadyExistsException, PasswordValidationException {
		if(userRepo.existsById(user.getId())) {
			throw new UserAlreadyExistsException();
		}
		if(user.getPassword().length() < 8) {
			throw new PasswordValidationException();
		}
		System.out.println(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		User res = userRepo.save(user);
		return res;
	}
	
	public String Login(User user) {
		String msg;
		if(userRepo.findByEmail(user.getEmail()) != null ) {
			User ema = userRepo.findByEmail(user.getEmail());
			System.out.println(ema);
			String s1 = ema.getPassword();
			String s2 = user.getPassword();
			System.out.println(" String1" +s1 + "String2" +s2);
			if(bCryptPasswordEncoder.matches(s2, s1)) 
			{
				System.out.println("Working..");
				msg = "LoggedIn Successfully";
			}
			else 
			{
				System.out.println("Failed..");
				msg = "Wrong Password! Please enter valid password.";
			}
		}else {
			msg = "User not found.";
		}
		System.out.println(msg);
		return msg;
	}
	
	@Override
    public User createUser(User user) throws UserAlreadyExistsException          //Create Users Record
    {
	      if(userRepo.existsById(user.getId()))
	      {
		      throw new UserAlreadyExistsException();
	      }
	      User u = userRepo.save(user);
	      return u;
    }
	 
	@Override
    public List<User> getAllUsersDetails()          //Get Users Record
	{
		   return (List<User>) userRepo.findAll();
    }

	@Override
	public User getUserDetailById(int userId) throws UserNotFoundException			//Get Users Record By User Id
	{
			Optional<User> us = this.userRepo.findById(userId);

	        if (us.isPresent()) {
	        	
	            return us.get();
	        } 
	        else
	        {
	        	throw new UserNotFoundException();
	        }
	 }

    @Override
    public User updateUser(User user)						//Update Users Record 
	   {
		   Optional <User> us =this.userRepo.findById(user.getId());
		   if(us.isPresent())
		   {
		   User userUpdate = us.get();
		   userUpdate.setId(user.getId());
		   userUpdate.setName(user.getName());
		   userUpdate.setEmail(user.getEmail());
		   userUpdate.setPassword(user.getPassword());
		   return userRepo.save(userUpdate);
		   }
	       else
	       {
			    return user;
	       }	
    }
  
    @Override
	public void deleteUserDetailByID(int userId)				//Delete User Record By Id
	{
	       Optional < User > user = this.userRepo.findById(userId);
	        if (user.isPresent())
	        {
	            this.userRepo.delete(user.get());
	        } 
	        else 
	        {
	          // return null;
	        }

	}
}
