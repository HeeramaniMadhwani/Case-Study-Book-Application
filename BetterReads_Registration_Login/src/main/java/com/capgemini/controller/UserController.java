package com.capgemini.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.PasswordValidationException;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;
import com.capgemini.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService uServ;

	public UserController(UserService uServ) {
		super();
		this.uServ = uServ;
	}

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/registration")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<User> Registration(@RequestBody User user) throws UserAlreadyExistsException, PasswordValidationException{
		User registeredUser = uServ.Registration(user);
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> Login(@RequestBody User user){
		String s = uServ.Login(user);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String test() 
	{
		return "Test Success";
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException
	{
	    User savedUser = uServ.createUser(user);
	    return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUsersDetails()
	{
		return new ResponseEntity<List<User>>((List<User>)uServ.getAllUsersDetails(),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/user/{userId}")
	public ResponseEntity <User> getUserDetailById(@PathVariable int userId) throws UserNotFoundException
	{
		return ResponseEntity.ok().body(uServ.getUserDetailById(userId));
    }

	@PutMapping("/users/{userId}")
    public ResponseEntity < User > updateUser(@PathVariable int userId, @RequestBody User user)
	{
        user.getId();
        return ResponseEntity.ok().body(this.uServ.updateUser(user));
    }
	
	@DeleteMapping("/users/{userId}")
    public HttpStatus deleteUserDetailByID(@PathVariable int userId)
	{
        this.uServ.deleteUserDetailByID(userId);
        return HttpStatus.NO_CONTENT;
    }
}
