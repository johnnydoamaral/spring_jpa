package com.johnny.springjpa.test;

import static org.junit.Assert.assertThat;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.empty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnny.springjpa.model.Person;
import com.johnny.springjpa.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTests {

	@Autowired
	PersonRepository repository;

	@Test
	public void testFindAll() {
		assertThat(repository.findAllPersons(), is(not(empty())));
	}

	@Test
	public void testFindById() {
		assertThat(repository.findPersonById(1001), is(not(nullValue())));
	}

	@Test
	public void testFindByName() {
		assertThat(repository.findPersonByName("Johnny"), is(not(empty())));
		assertThat(repository.findPersonByName("JOHNNY"), is(not(empty())));
		assertThat(repository.findPersonByName("johnny"), is(not(empty())));
		assertThat(repository.findPersonByName("someInexistentName"), is(empty()));
	}

	@Test
	public void testFindByLocation() {
		assertThat(repository.findPersonByLocation("Brazil"), is(not(empty())));
		assertThat(repository.findPersonByLocation("BRAZIL"), is(not(empty())));
		assertThat(repository.findPersonByLocation("brazil"), is(not(empty())));
		assertThat(repository.findPersonByLocation("someInexistentLocation"), is(empty()));
	}

	@Test
	public void testCreatePerson() {
		Person newPerson = new Person("Mark", "Germany", new Date());
		int newPersonId = repository.createPerson(newPerson).getId();
		assertThat(repository.findPersonById(newPersonId).getName(), equalTo(newPerson.getName()));
		assertThat(repository.findPersonById(newPersonId).getLocation(), equalTo(newPerson.getLocation()));
	}

	@Test
	public void testUpdatePerson() {
		Person newPerson = new Person("Donald", "Australia", new Date());
		int newPersonId = repository.createPerson(newPerson).getId();
		assertThat(repository.findPersonById(newPersonId).getName(), equalTo("Donald"));
		assertThat(repository.findPersonById(newPersonId).getLocation(), equalTo("Australia"));
		String newName = "Hugh", newLocation = "Malta";
		newPerson = repository.findPersonById(newPersonId);
		newPerson.setName(newName);
		newPerson.setLocation(newLocation);
		Person updatedPerson = repository.updatePerson(newPerson);
		assertThat(updatedPerson.getName(), equalTo(newName));
		assertThat(updatedPerson.getLocation(), equalTo(newLocation));

	}

	@Test
	public void testDeletePerson() {
		Person newPerson = new Person("Roger", "Cyprus", new Date());
		int newPersonId = repository.createPerson(newPerson).getId();
		repository.deletePersonById(newPersonId);
		assertThat(repository.findPersonById(newPersonId), is(nullValue()));
	}

}
