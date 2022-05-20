package com.capgemini.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

}
