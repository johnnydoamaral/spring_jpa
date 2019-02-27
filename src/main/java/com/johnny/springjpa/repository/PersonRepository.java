package com.johnny.springjpa.repository;

import java.util.List;

import com.johnny.springjpa.model.Person;

public interface PersonRepository {

	List<Person> findAllPersons();

	Person findPersonById(int id);

	List<Person> findPersonByName(String name);

	List<Person> findPersonByLocation(String location);

	void deletePersonById(int id);

	Person createPerson(Person person);

	Person updatePerson(Person person);
}
