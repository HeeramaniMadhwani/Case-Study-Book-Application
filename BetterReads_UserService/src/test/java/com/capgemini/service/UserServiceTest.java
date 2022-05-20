package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.model.User;
import com.capgemini.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest 
{
	@Mock
    private UserRepository ur;              //create a mock for BookRepo
	
    @InjectMocks
    private UsersServiceImpl url;         //Injects the mock as dependencies into Book Service Implementation
    
	@Test
	public void TestAddUser() throws UserAlreadyExistsException
	{	
		User u1 = new User(105,"Aditi","Bajaj","aditiB@gmail.com","JFS");        
		when(ur.save(any())).thenReturn(u1);
		url.createUser(u1);                      //Add user record into database
		verify(ur,times(1)).save(any());
		
	}

}
