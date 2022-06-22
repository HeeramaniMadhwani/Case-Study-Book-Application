package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.PasswordValidationException;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;

public interface UserService 
{
	public User Registration(User user) throws UserAlreadyExistsException, PasswordValidationException;
	public String Login(User user);
	public User createUser(User user) throws UserAlreadyExistsException; 
	 public List<User> getAllUsersDetails(); 
	 public User getUserDetailById(int userId) throws UserNotFoundException;
	 public User updateUser(User user);
	 public void deleteUserDetailByID(int userId);
	
}
