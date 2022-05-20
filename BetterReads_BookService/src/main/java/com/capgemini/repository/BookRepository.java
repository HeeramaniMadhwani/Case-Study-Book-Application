package com.capgemini.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>
{

}
