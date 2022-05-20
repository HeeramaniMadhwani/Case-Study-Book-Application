package com.capgemini.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.model.Book;
import com.capgemini.service.BooksService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class BookControllerTest
{
	@Autowired
	private MockMvc mockMvc;      //Created MockMvc object 
	
	@Mock
	private BooksService bs;          //Creating mock object for BookService Class
	private Book bk;             	 //Creating mock object for Book Class
	private List<Book> bkList;		 //Creating mock object for List Class

	@InjectMocks
	private BooksController bkc;		  //Injecting mock object in controller class
	
	@BeforeEach
	public void init()       //method for initializing object
	{
	      bk = new Book(20,"The Rochers","Rashmi Bansal",549,"Comics",1991);
	      mockMvc = MockMvcBuilders.standaloneSetup(bkc).build();
	}
		
	@Test
	public void addBookControllerTest() throws Exception
	{
		when(bs.createBook(any())).thenReturn(bk);
		mockMvc.perform(post("/api/v1/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(bk)))
		        .andExpect(status().isCreated());
		verify(bs,times(1)).createBook(any());
	}

	@Test 
	public void getAllBookControllerTest() throws Exception
	{
		when(bs.getAllBooks()).thenReturn(bkList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allBooks")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(asJSONString(bk)))
		       .andDo(MockMvcResultHandlers.print());
		verify(bs,times(1)).getAllBooks();	
	}	
	
	/*
	 * @Test public void getBookByIdTest() throws Exception { Book book = new
	 * Book(); book.setBook_id(2); book.setTitle("Proof");
	 * book.setAuthor("Dick Francis"); book.setPrice(349); book.setGenre("Mystery");
	 * book.setYear(1984); when(bs.getBookById(2)).thenReturn(book);
	 * mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/book/{book_id}"))
	 * .andExpect(status().isOk()) .andExpect((ResultMatcher) jsonPath("$.id"))
	 * .andExpect((ResultMatcher) jsonPath("$.title")); }
	 */
	
	@Test
	public void getAllEmpty() throws Exception
	{
		  when(bs.getAllBooks()).thenReturn(Collections.EMPTY_LIST);
		  mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allBooks"))
		         .andExpect(status().isOk());			  
	}
	
	
	public static String asJSONString(final Object obj) throws JsonProcessingException
	{
		return new ObjectMapper().writeValueAsString(obj);
	
	}
	
	
}
