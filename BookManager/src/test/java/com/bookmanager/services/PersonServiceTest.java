package com.bookmanager.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookmanager.model.Person;
import com.bookmanager.util.BookManagerUtil;

import utils.BookManagerContextLoader;

public class PersonServiceTest extends BookManagerContextLoader{

	@Autowired
	private PersonService personService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPersonList() {
		List<Person> personList = personService.getPersonList();
		BookManagerUtil.printPeople(personList);
		
		assertNotNull(personList);
		assertEquals(3, personList.size());
	}

}
