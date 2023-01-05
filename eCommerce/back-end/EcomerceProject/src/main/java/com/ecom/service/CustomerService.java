package com.ecom.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecom.Models.Customer;
import com.ecom.customExepciton.CustomerExepction;

public interface CustomerService {
	public Customer registerCustomer(Customer customer) throws CustomerExepction;
	public Customer updateCustomer(Customer customer, String key) throws CustomerExepction;
	public Customer deleteCustomerById(Integer id,String key) throws CustomerExepction;
	public List<Customer> getAllCustomers(String key) throws CustomerExepction;

}
