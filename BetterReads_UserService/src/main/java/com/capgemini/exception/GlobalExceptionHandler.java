package com.capgemini.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
	    @Value(value="${data.exception.userExists}")
		private String userExists;
	    
	    @ExceptionHandler(value=UserAlreadyExistsException.class)
		public ResponseEntity<String> UserAlreadyExistsException(UserAlreadyExistsException uex)
		{
			return new ResponseEntity<String>(userExists,HttpStatus.CONFLICT);
		}
	    
	    @Value(value="${data.exception.userNotFound}")
		private String userNotFound;
		
		@ExceptionHandler(value=UserNotFoundException.class)
		public ResponseEntity<String> UserNotFoundException(UserNotFoundException unf)
		{
			return new ResponseEntity<String>(userNotFound,HttpStatus.NOT_FOUND);
		}
	
}
