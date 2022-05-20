package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.BookAlreadyExistsException;
import com.capgemini.exception.BookNotFoundException;
import com.capgemini.model.Book;

public interface BooksService 
{
	 public Book createBook(Book bk) throws BookAlreadyExistsException;
	 public List<Book> getAllBooks(); 
	 public Book getBookById(int book_id) throws BookNotFoundException;
	 public void deleteBookByBookID(int book_id);
     public Book updateBook(Book bk);
}
