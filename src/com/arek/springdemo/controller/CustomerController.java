package com.arek.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arek.springdemo.dao.CustomerDAO;
import com.arek.springdemo.entity.Customer;
import com.arek.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

//	//Wstrzykujemy Data Access Object do kontrolera
//	@Autowired
//	private CustomerDAO customerDAO;
	
	//TERAZ WSTRZYKUJEMY SERVICE!
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//pobranie klientów z DAO
		List<Customer> theCustomers = customerService.getCustomers();
		
		//dodanie klientów  do modelu 
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		
		return "customer-form";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//zapisywanie klienta u¿ywaj¹c serwisu
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//Pobranie klienta z DB przez service
		Customer theCustomer = customerService.getCustomer(theId);
		
		//ustawienie customera
		theModel.addAttribute("customer", theCustomer);
		
		
		return "customer-form";
	}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}
