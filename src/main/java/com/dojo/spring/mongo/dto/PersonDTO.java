package com.dojo.spring.mongo.dto;

import com.dojo.spring.mongo.model.Person;

public class PersonDTO {
	
	private String id;
	private String name;
	private int age;
	private String email;
	
	 public PersonDTO() {
		 
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
	
	public PersonDTO(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.age = person.getAge();
		this.email = person.getEmail();
	}
}
