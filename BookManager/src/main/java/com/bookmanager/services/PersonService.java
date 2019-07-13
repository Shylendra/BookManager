package com.bookmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanager.model.Person;
import com.bookmanager.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person createPerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> createPersonAll(List<Person> people) {
		return personRepository.saveAll(people);
	}

	public List<Person> getPersonList() {
		return personRepository.findAll();
	}
	
	public Optional<Person> getPerson(Integer id) {
		return personRepository.findById(id);
	}

	
	public Person updatePerson(Person person) {
		return personRepository.saveAndFlush(person);
	}
	
	public boolean deletePerson(Integer id) {
		personRepository.deleteById(id);
		return true;
	}
	
}
