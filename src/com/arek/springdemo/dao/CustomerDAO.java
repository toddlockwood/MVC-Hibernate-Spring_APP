package com.arek.springdemo.dao;

import java.util.List;

import com.arek.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();
}
