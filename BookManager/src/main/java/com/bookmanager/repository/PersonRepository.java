package com.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.bookmanager.model.Person;

@RepositoryRestResource
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
