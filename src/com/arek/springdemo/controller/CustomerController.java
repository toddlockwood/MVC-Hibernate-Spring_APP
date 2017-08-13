package com.arek.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arek.springdemo.dao.CustomerDAO;
import com.arek.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//Wstrzykujemy Data Access Object do kontrolera
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		//pobranie klientów z DAO
		List<Customer> theCustomers = customerDAO.getCustomers();
		
		//dodanie klientów  do modelu 
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
}
