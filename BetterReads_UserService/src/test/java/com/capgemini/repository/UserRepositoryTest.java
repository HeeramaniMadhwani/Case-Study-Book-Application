package com.capgemini.repository;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.model.User;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest 
{

    @Autowired
	private UserRepository userRepo;
    
    @Test
	public void givenUserReturnsUserDetail() 
	{
		User u1 = new User(103,"Dhruv","Shah","dhruv@gmail.com","XYZ");  //User Input
		userRepo.save(u1);		
		
		User u2 = userRepo.findById(u1.getUserId()).get();         //Fetch data from database
		assertNotNull(u2);                 //To check whether it returns book object
		assertEquals(u1.getEmail(),u2.getEmail());
	}
    
    @Test
    public void getAllReturnAllUsers()
    {
    	User u1 = new User(103,"Dhruv","Shah","dhruv@gmail.com","XYZ");
    	userRepo.save(u1);
    	User u2 = new User(104,"Sanya","Arora","sanyaA@gmail.com","LMN");
    	userRepo.save(u2);
    	List<User>userList = (List<User>) userRepo.findAll();
    	assertEquals("Sanya",userList.get(5).getFirstName());
    	assertEquals(6,userList.size());
    }
}
