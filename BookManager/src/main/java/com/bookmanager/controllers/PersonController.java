package com.bookmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanager.exceptions.ResourceNotFoundException;
import com.bookmanager.model.Person;
import com.bookmanager.services.PersonService;

@RestController
@RequestMapping("/bookmanager")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	

	/* CREATE */
	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		logger.debug(person.toString());
		return new ResponseEntity<Person>(personService.createPerson(person), HttpStatus.CREATED);
	}
	
	/* READ */
	@GetMapping("/personAll")
	public ResponseEntity<List<Person>> getPersonsAll() {
		List<Person> personList = personService.getPersonList();
		if(personList.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Integer id) throws ResourceNotFoundException {
		
		Optional<Person> personReturned = personService.getPerson(id);
		if(!personReturned.isPresent()) {
			throw new ResourceNotFoundException(id, "Resource not found, invalid " + id + " requested.");
		}
		return new ResponseEntity<Person>(personReturned.get(), HttpStatus.OK);
	}
	
	/* UPDATE */
	@PutMapping("/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		return new ResponseEntity<Person>(personService.updatePerson(person), HttpStatus.OK);
	}

	/* DELETE */
	@DeleteMapping("/person/{id}")
	public boolean deletePerson(@PathVariable Integer id) {
		personService.deletePerson(id);
		return true;
	}	
}
