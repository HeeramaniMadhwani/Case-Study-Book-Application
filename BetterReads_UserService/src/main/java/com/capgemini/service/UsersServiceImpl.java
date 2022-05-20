package com.capgemini.service;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;
import com.capgemini.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersService 
{
	
	private UserRepository userRepo;

	 @Autowired
	 public UsersServiceImpl(UserRepository userRepo)
	 {
		super();
		this.userRepo = userRepo;
	 }
	 
	   @Override
      public User createUser(User user) throws UserAlreadyExistsException          //Create Users Record
      {
 	      if(userRepo.existsById(user.getUserId()))
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
		   Optional <User> us =this.userRepo.findById(user.getUserId());
		   if(us.isPresent())
		   {
		   User userUpdate = us.get();
		   userUpdate.setUserId(user.getUserId());
		   userUpdate.setFirstName(user.getFirstName());
		   userUpdate.setLastName(user.getLastName());
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
