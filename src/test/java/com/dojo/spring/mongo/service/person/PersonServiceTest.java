package com.dojo.spring.mongo.service.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.dojo.spring.mongo.dto.PersonDTO;
import com.dojo.spring.mongo.model.MetaData;
import com.dojo.spring.mongo.model.Person;
import com.dojo.spring.mongo.repository.PersonRepository;
import com.dojo.spring.mongo.service.PersonService;

@ExtendWith(value = MockitoExtension.class)
public class PersonServiceTest {
	@Mock
	private PersonRepository personRepository;
	@InjectMocks
	private PersonService personService;

	@Test
    public void shouldSaveANewPerson() throws Exception {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("Carlos");
        personDTO.setAge(18);
        personDTO.setEmail("carlos@teste.com.br");
        
        Person person = new Person(personDTO);
        person.setId("65eb5ee22cf13bb277fb7719");
        person.setExcluded(false);
        
        MetaData metadata = new MetaData();
        metadata.setCreateAt(LocalDateTime.now());
        metadata.setCreatedBy("65eb5f04cdbca45508a9fa7e");
        person.setMetadata(metadata);
        
        when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(person);
        
        PersonDTO savedPersonDTO = personService.create(personDTO, "65eb5f04cdbca45508a9fa7e");
        
        assertThat(savedPersonDTO).isNotNull();
        assertThat(savedPersonDTO.getName()).isEqualTo(personDTO.getName());
        assertThat(savedPersonDTO.getAge()).isEqualTo(personDTO.getAge());
        assertThat(savedPersonDTO.getEmail()).isEqualTo(personDTO.getEmail());
    }
	
	@Test
	public void shouldFindbyId() {
		Person person = new Person();
		person.setId("65eb0f27cad306db22c0ca2a");
		person.setName("Agnaldo");
		person.setAge(40);
		person.setEmail("agnaldo@teste.com.br");
		person.setExcluded(false);

		PersonDTO expectedDTO = new PersonDTO(person);

		when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

		PersonDTO actualDTO = personService.getByID(person.getId());

		assertThat(actualDTO).isEqualTo(expectedDTO);
	}
	
	@Test
    public void shouldGetAllPersons() {
        int pageNumber = 0;
        int pageSize = 10;
        List<Person> personList = createPersonList(pageSize);
        Page<Person> page = new PageImpl<>(personList);
        
        when(personRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(page);
        
        Page<PersonDTO> result = personService.getAll(pageNumber, pageSize);
        
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(pageSize);
    }
	
	@Test
    public void shouldUpdateExistingPerson() {
        String userId = "65eb6a7797c07d3ff0c7e033";
        String personId = "65eb6a720ec948b048134031";
        
        Person existingPerson = new Person();
        existingPerson.setId(personId);
        existingPerson.setName("John Doe");
        existingPerson.setAge(30);
        existingPerson.setEmail("john.doe@example.com");
        existingPerson.setExcluded(false);
        existingPerson.setMetadata(new MetaData());
        
        PersonDTO updatedPersonDto = new PersonDTO();
        updatedPersonDto.setId(personId);
        updatedPersonDto.setName("Jane Doe");
        updatedPersonDto.setAge(35);
        updatedPersonDto.setEmail("jane.doe@example.com");
        
        when(personRepository.findById(personId)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(existingPerson)).thenReturn(existingPerson);
        
        PersonDTO updatedDto = personService.update(updatedPersonDto, userId);
        
        assertThat(updatedDto).isNotNull();
        assertThat(updatedDto.getId()).isEqualTo(personId);
        assertThat(updatedDto.getName()).isEqualTo(updatedPersonDto.getName());
        assertThat(updatedDto.getAge()).isEqualTo(updatedPersonDto.getAge());
        assertThat(updatedDto.getEmail()).isEqualTo(updatedPersonDto.getEmail());
    }
	
	  @Test
	    public void shouldDeleteExistingPerson() {
	        String personId = "65eb6af87b0dc2c9c6f09346";
	        when(personRepository.existsById(personId)).thenReturn(true);
	        personService.delete(personId);
	        verify(personRepository).deleteById(personId);
	    }
	    
    
    private List<Person> createPersonList(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> {
                    Person person = new Person();
                    person.setId("id" + i);
                    person.setName("Person " + i);
                    person.setAge(20 + i);
                    person.setEmail("person" + i + "@example.com");
                    return person;
                })
                .collect(Collectors.toList());
    }
}
