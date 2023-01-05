package com.ecom.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Models.AdminSession;
import com.ecom.Models.Customer;
import com.ecom.Models.LoginSession;
import com.ecom.Repo.AdminSesstionRepo;
import com.ecom.Repo.CustomerRepo;
import com.ecom.Repo.LoginSessionRepo;
import com.ecom.customExepciton.CustomerExepction;
import com.ecom.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private LoginSessionRepo loginSessionRepo;
	
	@Autowired
	private AdminSesstionRepo adminSesstionRepo;
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerExepction {
		Optional<Customer> customerOption= customerRepo.findByEmail(customer.getEmail());
		if(customerOption.isPresent()) {
			throw new CustomerExepction("This Eamil id has already in use");
		}
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerExepction {
		LoginSession loginSession= this.permissionTo(key);
		if(loginSession!=null) {
			customer.setCustomerId(loginSession.getCustomerId());
			return customerRepo.save(customer);
		}
		throw new CustomerExepction("Please login before updating");
	}

	@Override
	public Customer deleteCustomerById(Integer id, String key) throws CustomerExepction {
		AdminSession adminSession= this.permissionforAdminLogin(key);
		if(adminSession!=null) {
			Optional<LoginSession> lOptiona= loginSessionRepo.findById(id);
			Optional<Customer> cuOptional= customerRepo.findById(id);
			if(lOptiona.isPresent() || cuOptional.isPresent()) {
				loginSessionRepo.delete(lOptiona.get());
				customerRepo.delete(cuOptional.get());
				return cuOptional.get();
			}
			throw new CustomerExepction("Data not found of this id");
		}
		throw new CustomerExepction("You have to login as admin for this");
	}

	@Override
	public List<Customer> getAllCustomers(String key) throws CustomerExepction {
		if(this.permissionforAdminLogin(key)!=null) {
			return customerRepo.findAll();
		}
		throw new CustomerExepction("You are right persone for this");
	}	
	
	// method of checking user
	public LoginSession permissionTo(String key) {
		
		Optional<LoginSession> optional= loginSessionRepo.findByKey(key);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}	
			
	// method for permission of admin 
	public AdminSession permissionforAdminLogin(String key) {
		
		Optional<AdminSession> optional= adminSesstionRepo.findByKey(key);
	    if(optional.isPresent()) {
				return optional.get();
			}
			return null;
		}

	
}
