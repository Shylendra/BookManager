package com.bookmanager.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.ResourceUtils;

import com.bookmanager.model.Book;
import com.bookmanager.model.Person;
import com.bookmanager.services.BookManagerCsvService;
import com.bookmanager.services.BookManagerCsvServiceImpl;
import com.bookmanager.services.BookService;
import com.bookmanager.services.PersonService;
import com.bookmanager.util.BookManagerUtil;

@SpringBootApplication
@ComponentScan({"com.bookmanager*"})
@EntityScan({"com.bookmanager*"})
@EnableJpaRepositories({"com.bookmanager*"})
public class BookManagerApp implements CommandLineRunner{

	private BookManagerCsvService bookManagerCsvService;
	
	@Autowired
	private PersonService personService;

	@Autowired
	private BookService bookService;

	private static final String PEOPLE_CSV = "people-input.csv";
	
	private static final String BOOKS_CSV = "books-input.csv";
	
	public static void main(String[] args) {
		SpringApplication.run(BookManagerApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		/* Read People from CSV input */
		bookManagerCsvService = new BookManagerCsvServiceImpl<Person>();
		List<Person> people = bookManagerCsvService.read(BookManagerUtil.getAbsoluteResourcePath(PEOPLE_CSV), new String[]{"name","phoneNumber","emailAddress"}, Person.class);
		BookManagerUtil.printPeople(people);

		/* Create People */
		List<Person> saveAll = personService.createPersonAll(people);
		BookManagerUtil.printPeople(saveAll);
		
		/* Read Books from CSV input */
		bookManagerCsvService = new BookManagerCsvServiceImpl<Book>();
		List<Book> books = bookManagerCsvService.read(BookManagerUtil.getAbsoluteResourcePath(BOOKS_CSV), new String[]{"title","author","isbn"}, Book.class);
		BookManagerUtil.printBooks(books);

		/* Update Books */
		for(int i=0;i<saveAll.size();i++) {
			books.get(i).setBorrowedByPersonId(saveAll.get(i).getId());
		}
		books.get(books.size()-1).setBorrowedByPersonId(saveAll.get(0).getId());
		BookManagerUtil.printBooks(books);

		/* Create Books */
		List<Book> savedAllBooks = bookService.createBooksAll(books);
		BookManagerUtil.printBooks(savedAllBooks);

		
		/* Update Person */
		for(Person p: saveAll) {
			p.setBooksBorrowed(getBooksBorrowedCount(books, p.getId()));
			personService.updatePerson(p);
		}
		
	}
	
	public int getBooksBorrowedCount(List<Book> books, Integer personId) {
		int booksCount = (int) books.stream().filter(c -> c.getBorrowedByPersonId() == personId)
		.count();
		return booksCount;
	}

}
