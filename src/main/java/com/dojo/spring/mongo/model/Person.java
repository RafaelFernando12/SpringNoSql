package com.dojo.spring.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dojo.spring.mongo.dto.PersonDTO;

@Document(collection = "person")
public class Person {
	
	@Id
	private String id;
	private String name;
	private int age;
	private String email;
	private Boolean excluded;
	private MetaData metadata;
	
	public Person() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getExcluded() {
		return excluded;
	}
	public void setExcluded(Boolean excluded) {
		this.excluded = excluded;
	}
	public MetaData getMetadata() {
		return metadata;
	}
	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}
	public Person(PersonDTO personDto) {
		this.id = personDto.getId();
		this.name = personDto.getName();
		this.age = personDto.getAge();
		this.email = personDto.getEmail();
	}
}
