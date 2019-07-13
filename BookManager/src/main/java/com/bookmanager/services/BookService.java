package com.bookmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanager.model.Book;
import com.bookmanager.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findByPersonId(Integer personId) {
		return bookRepository.findByPersonId(personId);
	}

	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> createBooksAll(List<Book> books) {
		return bookRepository.saveAll(books);
	}

	public List<Book> getBookList() {
		return bookRepository.findAll();
	}
	
	public Optional<Book> getBook(Integer id) {
		return bookRepository.findById(id);
	}

	
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}
	
	public boolean deleteBook(Integer id) {
		bookRepository.deleteById(id);
		return true;
	}	
}
