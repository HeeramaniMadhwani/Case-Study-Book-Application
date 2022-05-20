package com.capgemini.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;
import com.capgemini.repository.UserRepository;
import com.capgemini.service.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UserControllerTest 
{
	@Autowired
	private MockMvc mockMvc;      //Created MockMvc object 
	
	@Mock
	private UsersService us;          //Creating mock object for BookService Class
	private User user;             	 //Creating mock object for Book Class
	private List<User> userList;		 //Creating mock object for List Class
    private Optional<User> user1;
	@MockBean
	private UserRepository userRepos;
	
	@InjectMocks
	private UsersController userc;		  //Injecting mock object in controller class
	
	@BeforeEach
	public void init()       //method for initializing object
	{
	      user = new User(103,"Dhruv","Shah","dhruv@gmail.com","XYZ");
	      mockMvc = MockMvcBuilders.standaloneSetup(userc).build();
	}
	
	@Test
	public void addUserControllerTest() throws Exception
	{
		when(us.createUser(any())).thenReturn(user);
		mockMvc.perform(post("/api/u1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(user)))
		        .andExpect(status().isCreated());
		verify(us,times(1)).createUser(any());
	}
	
	@Test 
	public void getAllUserControllerTest() throws Exception
	{
		when(us.getAllUsersDetails()).thenReturn(userList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/u1/allUsers")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(asJSONString(user)))
		       .andDo(MockMvcResultHandlers.print());
		verify(us,times(1)).getAllUsersDetails();	
	}	
	
	/*
	 * @Test public void getUserDetailByIdControllerTest(@PathVariable int userId)
	 * throws Throwable { user.setUserId(userId);
	 * when((us.getUserDetailById(userId))).thenReturn(user);
	 * mockMvc.perform(MockMvcRequestBuilders.get("/api/u1/user/{userId}")
	 * .contentType(MediaType.APPLICATION_JSON) .content(asJSONString(user)))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$.id").value(userId))
	 * .andDo(MockMvcResultHandlers.print());
	 * verify(us,times(1)).getUserDetailById(userId); }
	 */
	
	
	public static String asJSONString(final Object obj) throws JsonProcessingException
	{
		return new ObjectMapper().writeValueAsString(obj);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
