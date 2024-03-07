package com.dojo.spring.mongo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dojo.spring.mongo.dto.PersonDTO;
import com.dojo.spring.mongo.model.MetaData;
import com.dojo.spring.mongo.model.Person;
import com.dojo.spring.mongo.repository.PersonRepository;
import com.dojo.spring.mongo.utils.Utils;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Transactional(rollbackFor = Exception.class)
	public PersonDTO create(PersonDTO personDTO, String userID) throws Exception {
		try {
			Person person = new Person(personDTO);
			person.setExcluded(false);

			MetaData metadata= new MetaData();
			metadata.setCreateAt(LocalDateTime.now());
			metadata.setCreatedBy(userID);
			
			person.setMetadata(metadata);
			Person createdPerson = personRepository.save(person);
			return new PersonDTO(createdPerson);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public PersonDTO getByID(String id) {
		Optional<Person> person = personRepository.findById(id);
		 return person.map(PersonDTO::new).orElse(null);
	}
	
	public Page<PersonDTO> getAll(int pageNumber, int pageSize) {
		   PageRequest pageable = PageRequest.of(pageNumber, pageSize);
		   Page<Person> people = personRepository.findAll(pageable);
		   return people.map(PersonDTO::new);
	}
	
	  public PersonDTO update(PersonDTO updatedPersonDto, String userID) {
	       Optional<Person> person = personRepository.findById(updatedPersonDto.getId());
	        if (person.isPresent()) {
	        	Person existingPerson = person.get();
	            BeanUtils.copyProperties(updatedPersonDto, existingPerson, Utils.getNullPropertyNames(updatedPersonDto));
	            existingPerson.getMetadata().setUpdatedAt(LocalDateTime.now());
	            existingPerson.getMetadata().setUpdatedBy(userID);
	            personRepository.save(existingPerson);
	            return new PersonDTO(existingPerson);
	        } else {
	            return null;
	        }
	    }

	public void delete(String id) {
		personRepository.deleteById(id);
	}
}
