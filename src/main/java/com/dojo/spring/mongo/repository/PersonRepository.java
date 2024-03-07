package com.dojo.spring.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dojo.spring.mongo.model.Person;

public interface PersonRepository extends MongoRepository<Person, String>{
	//Page<Person> findAll(Pageable pageable);
}
