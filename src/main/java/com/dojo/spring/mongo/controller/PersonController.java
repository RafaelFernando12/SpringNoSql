package com.dojo.spring.mongo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dojo.spring.mongo.dto.PersonDTO;
import com.dojo.spring.mongo.service.PersonService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/ping")
	public String healthy() {
		return "ok";
	}

	@PostMapping()
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO, @RequestHeader String userID)
			throws Exception {
		PersonDTO personCreated = personService.create(personDTO, userID);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(personCreated.getId())
				.toUri();
		return ResponseEntity.created(uri).body(personCreated);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> getByID(@PathVariable String id) {
		PersonDTO personDTO = personService.getByID(id);
		if (personDTO != null) {
			return ResponseEntity.ok(personDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping()
	public ResponseEntity<Page<PersonDTO>> getAll(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		return ResponseEntity.ok(personService.getAll(pageNumber, pageSize));
	}
	
	@PutMapping()
	public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO, @RequestHeader String userID) {
		return ResponseEntity.ok(personService.update(personDTO, userID));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
