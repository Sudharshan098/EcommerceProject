package com.ecom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Models.Customer;
import com.ecom.customExepciton.CustomerExepction;
import com.ecom.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer/reagister")
	public ResponseEntity<Customer> ragiserCustomer(@Valid @RequestBody Customer customer) throws CustomerExepction{
		Customer customer2= customerService.registerCustomer(customer);
		return new ResponseEntity<Customer>(customer2, HttpStatus.CREATED);
	}
	@PutMapping("/customer/update")
	public ResponseEntity<Customer> updateCustomerHandller(@RequestBody Customer customer,@RequestHeader("token") String key) throws CustomerExepction{
		Customer customer2= customerService.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(customer2, HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("admin/customer/delete/{id}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable("id") Integer id,@RequestHeader("token") String key) throws CustomerExepction{
		Customer customer= customerService.deleteCustomerById(id, key);
		return new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);
	}
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader("token") String token) throws CustomerExepction{
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(token), HttpStatus.OK);
		
	}
	
}
