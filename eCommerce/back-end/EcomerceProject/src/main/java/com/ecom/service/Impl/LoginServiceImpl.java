package com.ecom.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Models.Customer;
import com.ecom.Models.LoginSession;
import com.ecom.ModelsDto.LoginDto;
import com.ecom.Repo.CustomerRepo;
import com.ecom.Repo.LoginSessionRepo;
import com.ecom.customExepciton.CustomerExepction;
import com.ecom.service.LoginService;

import net.bytebuddy.utility.RandomString;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginSessionRepo loginSessionRepo;
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public String loginUser(LoginDto loginDto) throws CustomerExepction {
		Optional<Customer> customer= customerRepo.findByEmail(loginDto.getEmail());
		if(customer.isPresent()) {
			if(customer.get().getPassword().equals(loginDto.getPassword())) {
				Optional<LoginSession> exitLoginSession= loginSessionRepo.findById(customer.get().getCustomerId());
				if(exitLoginSession.isPresent()) {
					throw new CustomerExepction("User already LogedIn");
				}
				else {
					LoginSession loginSession=new LoginSession();
					loginSession.setCustomerId(customer.get().getCustomerId());
					String key=RandomString.make(10);
					loginSession.setKey(key);
					loginSession.setDateTime(LocalDateTime.now());
					return loginSessionRepo.save(loginSession).getKey();
				}
				
			}
			else {
				throw new CustomerExepction("Please Enter Your Valid Password");
			}
		}
		else {
			throw new CustomerExepction("Please Enter Your Valid Email Id");
		}
		
	}

	@Override
	public String logOut(String key) throws CustomerExepction {
	 Optional<LoginSession> loginSession=loginSessionRepo.findByKey(key);
	 if(loginSession.isPresent()) {
		 loginSessionRepo.deleteById(loginSession.get().getCustomerId());
		 return "You are Loged out";
	 }
		throw new CustomerExepction("Please Enter Valid Key to Logout");
	}

}
