package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.capgemini.exception.BookAlreadyExistsException;
import com.capgemini.exception.BookNotFoundException;
import com.capgemini.model.Book;
import com.capgemini.service.BooksService;

@RestController
@RequestMapping("/api/v1")
public class BooksController
{   
	@Autowired
	private BooksService bookServ;

	public BooksController(BooksService bookServ)
	{
		super();
		this.bookServ = bookServ;
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) throws BookAlreadyExistsException
	{
	    Book savedBook = bookServ.createBook(book);
	    return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
	}
	
	@GetMapping("/allBooks")
	public ResponseEntity<List<Book>> getAllBooks()
	{
		return new ResponseEntity<List<Book>>((List<Book>)bookServ.getAllBooks(),HttpStatus.OK);
	}

	@GetMapping("/book/{book_id}")
	public ResponseEntity<Book> getBookById(@PathVariable int book_id) throws BookNotFoundException
	{
		Book getBook = bookServ.getBookById(book_id);
		return new ResponseEntity<>(getBook,HttpStatus.FOUND);
		//return ResponseEntity.ok().body(bookServ.getBookById(book_id));
    }
	
	@PutMapping("/books/{book_id}")
    public ResponseEntity < Book > updateBook(@PathVariable int book_id, @RequestBody Book book)
	{
        book.setBook_id(book_id);
        return ResponseEntity.ok().body(this.bookServ.updateBook(book));
    }
	
	@DeleteMapping("/book/{book_id}")
    public HttpStatus deleteBookByBookID(@PathVariable int book_id)
	{
        this.bookServ.deleteBookByBookID(book_id);
        return HttpStatus.NO_CONTENT;
    }

}
