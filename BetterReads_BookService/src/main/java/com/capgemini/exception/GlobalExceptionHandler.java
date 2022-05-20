package com.capgemini.exception;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler 
{
    @Value(value="${data.exception.msg}")
	private String msg;
    
    @ExceptionHandler(value=BookAlreadyExistsException.class)
	public ResponseEntity<String> BookAlreadyExistsException(BookAlreadyExistsException bex)
	{
		return new ResponseEntity<String>(msg,HttpStatus.CONFLICT);
	}
    
    @Value(value="${data.exception.bookNotFound}")
	private String bookNotFound;
	
	@ExceptionHandler(value=BookNotFoundException.class)
	public ResponseEntity<String> BookNotFoundException(BookNotFoundException bnf)
	{
		return new ResponseEntity<String>(bookNotFound,HttpStatus.NOT_FOUND);
	}
}
