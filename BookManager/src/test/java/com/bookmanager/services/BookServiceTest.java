package com.bookmanager.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookmanager.model.Book;
import com.bookmanager.model.Person;
import com.bookmanager.util.BookManagerUtil;

import utils.BookManagerContextLoader;

public class BookServiceTest extends BookManagerContextLoader{

	@Autowired
	private PersonService personService;

	@Autowired
	private BookService bookService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetBookList() {
		List<Book> bookList = bookService.getBookList();
		BookManagerUtil.printBooks(bookList);
		
		assertNotNull(bookList);
		assertEquals(4, bookList.size());
	}

	@Test
	public void testFindByPersonId() {
		List<Person> personList = personService.getPersonList();
		for(Person person:personList) {
			List<Book> booksFoundByPersonId = bookService.findByPersonId(person.getId());
			System.out.println("Books for Person["+person.getId() + ", "+ person.getName() +"]: ");
			BookManagerUtil.printBooks(booksFoundByPersonId);
		}
	}
	
}
