package com.bookmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.bookmanager.model.Book;

@RepositoryRestResource
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query("SELECT b FROM Book b WHERE b.borrowedByPersonId =:personId")
	public List<Book> findByPersonId(@Param("personId") Integer personId);
}
