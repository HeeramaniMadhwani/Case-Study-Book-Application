package com.capgemini.model;


import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="books")
public class Book
{
    @Id
    private int book_id;
	private String title;
	public String author;
	public double price;
	public String genre;
	public int year;
	
	//Default Constructor
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public Book(int book_id, String title, String author, double price, String genre, int year) 
	{
		super();
		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.genre = genre;
		this.year = year;
	}

	//Getters and Setters
	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", author=" + author + ", price=" + price + ", genre="
				+ genre + ", year=" + year + "]";
	}

}
