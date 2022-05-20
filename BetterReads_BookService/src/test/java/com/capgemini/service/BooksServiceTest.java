package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.apache.http.client.methods.RequestBuilder;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.exception.BookAlreadyExistsException;
import com.capgemini.exception.BookNotFoundException;
import com.capgemini.model.Book;
import com.capgemini.repository.BookRepository;
import com.google.common.net.MediaType;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BooksServiceTest 
{
	@Mock
    private BookRepository br;              //create a mock for BookRepo
	private Book book;
	
    @InjectMocks
    private BooksServiceImpl brl;         //Injects the mock as dependencies into Book Service Implementation
	
	@Test
	public void TestAddBook() throws BookAlreadyExistsException
	{
		Book b1 = new Book(16,"The Firm","Rashmi Bansal",549,"Thriller",1991); 
		when(br.save(any())).thenReturn(b1);
		brl.createBook(b1);    //Add book data into database
		verify(br,times(1)).save(any());
	}

	@Test
	public void testGetAllBooks()
	{
		Book b1 = new Book(1,"The Firm","Rashmi Bansal",549,"Thriller",1991);      //user input
		br.save(b1);     //saves data into database
		Book b2 = new Book(2,"Proof", "Dick Francis", 349, "Mystery",1984);     //user input
		br.save(b2);     //saves data into database
		Book b3 = new Book(3,"Animal Farm", "George Orwell", 250, "Classics",1945);     //user input
		br.save(b3);     //saves data into database
		
		List<Book> bkList =new ArrayList<>();   //creating list object
		bkList.add(b1);       //adding object data into list
		bkList.add(b2);
		bkList.add(b3);
		
	    when(br.findAll()).thenReturn(bkList);
	    List<Book> bkList1 = brl.getAllBooks();    //creating another object for list
	    assertEquals(bkList,bkList1);              //checking both list are same 
	    verify(br,times(1)).save(b1);
	    verify(br,times(1)).findAll();
     }	

	@Test
	public void findBookById() throws BookNotFoundException
	{
		Mockito.when(br.findById(1)).thenReturn(Optional.of(new Book(1,"Hangover","Tim",299,"Mystery",1988)));
		
		Book book = brl.getBookById(1);
		assertEquals("Hangover",book.getTitle());
		assertEquals("Tim",book.getAuthor());
		assertEquals(299,book.getPrice());
	    assertEquals("Mystery",book.getGenre());	
	    assertEquals(1988,book.getYear());
	}
	
	/*
	 * @Test public void testDelete() { Book book = new Book(); book.setBook_id(1);
	 * book.setTitle("Untouchable"); book.setAuthor("Mulk Raj Anand");
	 * book.setPrice(250); book.setGenre("Classics"); book.setYear(1988);
	 * Optional<Book> ofResult = Optional.of(book);
	 * doNothing().when(this.br).deleteById((Integer) any());
	 * when(this.br.findById((Integer) any())).thenReturn(ofResult);
	 * verify(this.br).deleteById((Integer) any());
	 * verify(this.br).findById((Integer) any());
	 * assertTrue(this.brl.getAllBooks().isEmpty()); }
	 */
}