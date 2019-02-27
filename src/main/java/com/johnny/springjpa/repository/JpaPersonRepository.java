package com.johnny.springjpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.johnny.springjpa.model.Person;

@Repository
@Transactional
public class JpaPersonRepository implements PersonRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Person> findAllPersons() {
		return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
	}

	@Override
	public Person findPersonById(int id) {
		return entityManager.find(Person.class, id);
	}

	@Override
	public List<Person> findPersonByName(String name) {
		return entityManager
				.createQuery("SELECT p FROM Person p WHERE UPPER(p.name) LIKE UPPER(:personName)", Person.class)
				.setParameter("personName", name).getResultList();
	}

	@Override
	public List<Person> findPersonByLocation(String location) {
		return entityManager
				.createQuery("SELECT p FROM Person p WHERE UPPER(p.location) LIKE UPPER(:personLocation)", Person.class)
				.setParameter("personLocation", location).getResultList();
	}

	@Override
	public void deletePersonById(int id) {
		Person person = findPersonById(id);
		entityManager.remove(person);
	}

	@Override
	public Person createPerson(Person person) {
		return entityManager.merge(person);
	}

	@Override
	public Person updatePerson(Person person) {
		return entityManager.merge(person);
	}

}
