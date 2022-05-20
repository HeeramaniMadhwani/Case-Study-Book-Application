package com.capgemini.exception;

public class BookAlreadyExistsException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException() //default constructor
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public BookAlreadyExistsException(String message)    //parameterized constructor
	{
		super(message);
	}
}
