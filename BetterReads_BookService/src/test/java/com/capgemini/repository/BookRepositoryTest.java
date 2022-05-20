package com.capgemini.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.model.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest
{
	    @Autowired
		private BookRepository bookRepos;
		
		@Test
		public void givenBookReturnsBook() 
		{
			Book b1 = new Book(6,"Animal Farm", "George Orwell", 250, "Classics",1945);  //User Input
			bookRepos.save(b1);		
			
			Book b2 = bookRepos.findById(b1.getBook_id()).get();   //Fetch data from database
			assertNotNull(b2);                 //To check whether it returns book object
			assertEquals(b1.getTitle(),b2.getTitle());
		}
		
	    @Test
	    public void getAllReturnAllBooks()
	    {
	    	Book b3 = new Book(2,"Proof", "Dick Francis", 349, "Mystery",1984);   //user input
	    	bookRepos.save(b3);
	    	Book b4 = new Book(3,"The Secret","Rhonda Byrne",299,"Non Fiction",2006);        //user input
	    	bookRepos.save(b4);
	    	
	    	List<Book>bookList = (List<Book>) bookRepos.findAll();
	    	assertEquals("Proof",bookList.get(1).getTitle());
	    	assertEquals(16,bookList.size());
	    }
	    
	    @Test
	    public void testFindBookById()
	    {
	    	Optional<Book> books = bookRepos.findById(1);		//Action
	    	assertTrue(books.isPresent());						//Assertion
	    }
	    
	    @Test
	    public void testFindBookById_NotFound()
	    {
	    	Optional<Book> books = bookRepos.findById(21);        //Action
	    	assertFalse(books.isPresent());						//Assertion
	    }	    
}

