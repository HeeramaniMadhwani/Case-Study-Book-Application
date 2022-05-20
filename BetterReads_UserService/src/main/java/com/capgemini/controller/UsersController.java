package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;
import com.capgemini.service.UsersService;


@RestController
@RequestMapping("/api/u1")
public class UsersController
{
	@Autowired
	private UsersService userServ;

	public UsersController(UsersService userServ)
	{
		super();
		this.userServ = userServ;
	}
	
	@Autowired 
	private RestTemplate restTemplate;
	
	@GetMapping("/books")        //get all book details in json
	public String getBooks()
	{
	String response = restTemplate.getForObject("http://localhost:8085/api/v1/allBooks", String.class);
	return response;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException
	{
	    User savedUser = userServ.createUser(user);
	    return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUsersDetails()
	{
		return new ResponseEntity<List<User>>((List<User>)userServ.getAllUsersDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity <User> getUserDetailById(@PathVariable int userId) throws UserNotFoundException
	{
		return ResponseEntity.ok().body(userServ.getUserDetailById(userId));
    }
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/users/{userId}")
    public ResponseEntity < User > updateUser(@PathVariable int userId, @RequestBody User user)
	{
        user.getUserId();
        return ResponseEntity.ok().body(this.userServ.updateUser(user));
    }
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{userId}")
    public HttpStatus deleteUserDetailByID(@PathVariable int userId)
	{
        this.userServ.deleteUserDetailByID(userId);
        return HttpStatus.NO_CONTENT;
    }
}