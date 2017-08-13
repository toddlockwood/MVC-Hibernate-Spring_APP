package com.arek.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arek.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Wstrzykujemy SessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Customer> getCustomers() {

		// pobranie obecnej sesji
		Session currentSession = sessionFactory.getCurrentSession();

		// stworzenie zapytania
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
		// wykonanie zapytania
		List<Customer> customers = theQuery.getResultList();

		// zwócenie wyniku

		return customers;
	}

}
