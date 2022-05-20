package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exception.BookAlreadyExistsException;
import com.capgemini.exception.BookNotFoundException;
import com.capgemini.model.Book;
import com.capgemini.repository.BookRepository;

@Service
public class BooksServiceImpl implements BooksService 
{
	 private BookRepository bookRepos;

	 @Autowired
	 public BooksServiceImpl(BookRepository bookRepos)
	 {
		super();
		this.bookRepos = bookRepos;
	 }
	 
	   @Override
       public Book createBook(Book bk) throws BookAlreadyExistsException      //Create Books Record
       {
  	      if(bookRepos.existsById(bk.getBook_id()))
  	      {
  		      throw new BookAlreadyExistsException();
  	      }
  	      Book b = bookRepos.save(bk);
  	      return b;
       }
	 
	   @Override
       public List<Book> getAllBooks()           //Get Books Record
 	   {
  		   return (List<Book>) bookRepos.findAll();
 	   }

	   @Override
	   public Book getBookById(int book_id) throws BookNotFoundException
	   {
			Optional<Book> bk = this.bookRepos.findById(book_id);

	        if (bk.isPresent()) {
	        	
	            return bk.get();
	        } 
	        else
	        {
	        	throw new BookNotFoundException();
	        }
		}

       @Override
       public Book updateBook(Book bk)						//Update Books Record
 	   {
 		   Optional <Book> book =this.bookRepos.findById(bk.getBook_id());
 		   if(book.isPresent())
 		   {
 		   Book bookUpdate = book.get();
 		   bookUpdate.setBook_id(bk.getBook_id());
 		   bookUpdate.setTitle(bk.getTitle());
 		   bookUpdate.setAuthor(bk.getAuthor());
 		   bookUpdate.setPrice(bk.getPrice());
 	       bookUpdate.setYear(bk.getYear());
 	       bookRepos.save(bookUpdate);
 		   return bookUpdate;
 		   }
 	       else
 	       {
 			    return bk;
 	       }	
       }
     
       @Override
	   public void deleteBookByBookID(int book_id)					//Delete Book Record By Id
	   {
	       Optional <Book> book = this.bookRepos.findById(book_id);
	        if (book.isPresent())
	        {
	            this.bookRepos.delete(book.get());
	        } 
	        else 
	        {
	          // return null;
	        }

	   }


}
