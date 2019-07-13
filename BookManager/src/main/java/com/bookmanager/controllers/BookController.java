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
import com.bookmanager.model.Book;
import com.bookmanager.services.BookService;

@RestController
@RequestMapping("/bookmanager")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	/* CREATE */
	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		logger.debug(book.toString());
		return new ResponseEntity<Book>(bookService.createBook(book), HttpStatus.CREATED);
	}
	
	/* READ */
	@GetMapping("/bookAll")
	public ResponseEntity<List<Book>> getBooksAll() {
		List<Book> bookList = bookService.getBookList();
		if(bookList.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	@GetMapping("/bookAll/{id}")
	public ResponseEntity<List<Book>> getBooksByPersonId(@PathVariable Integer id) {
		List<Book> bookList = bookService.findByPersonId(id);
		if(bookList.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}

	@GetMapping("/booksCount/{id}")
	public ResponseEntity<Integer> getBooksCountByPersonId(@PathVariable Integer id) {
		ResponseEntity<List<Book>> bookList = getBooksByPersonId(id);
		return new ResponseEntity<Integer>(bookList.getBody().size(), HttpStatus.OK);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Integer id) throws ResourceNotFoundException {
		
		Optional<Book> bookReturned = bookService.getBook(id);
		if(!bookReturned.isPresent()) {
			throw new ResourceNotFoundException(id, "Resource not found, invalid " + id + " requested.");
		}
		return new ResponseEntity<Book>(bookReturned.get(), HttpStatus.OK);
	}
	
	/* UPDATE */
	@PutMapping("/book")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(bookService.updateBook(book), HttpStatus.OK);
	}

	/* DELETE */
	@DeleteMapping("/book/{id}")
	public boolean deleteBook(@PathVariable Integer id) {
		bookService.deleteBook(id);
		return true;
	}	
}
