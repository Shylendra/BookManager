package com.bookmanager.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bookmanager.model.Book;
import com.bookmanager.model.Person;

public class BookManagerCsvServiceTest {
	
	private BookManagerCsvService bookManagerCsvService;
	
	private static final String PEOPLE_CSV = "./people-input.csv";
	
	private static final String BOOKS_CSV = "./books-input.csv";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadPerson() throws IOException {
		bookManagerCsvService = new BookManagerCsvServiceImpl<Person>();
		List<Person> people = bookManagerCsvService.read(PEOPLE_CSV, new String[]{"name","phoneNumber","emailAddress"}, Person.class);
		
		people.forEach(p -> {
			System.out.println(p);
		});
		
		assertNotNull(people);
		assertEquals(3, people.size());
	}


	@Test
	public void testReadBook() throws IOException {
		bookManagerCsvService = new BookManagerCsvServiceImpl<Book>();
		List<Book> books = bookManagerCsvService.read(BOOKS_CSV, new String[]{"title","author","isbn"}, Book.class);
		
		books.forEach(p -> {
			System.out.println(p);
		});
		
		assertNotNull(books);
		assertEquals(4, books.size());
	}
	
}
