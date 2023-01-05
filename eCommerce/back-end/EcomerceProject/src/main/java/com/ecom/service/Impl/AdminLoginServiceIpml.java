package com.ecom.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Models.Admin;
import com.ecom.Models.AdminSession;
import com.ecom.Models.LoginSession;
import com.ecom.ModelsDto.LoginDto;
import com.ecom.Repo.AdminRepo;
import com.ecom.Repo.AdminSesstionRepo;
import com.ecom.customExepciton.AdminExecption;
import com.ecom.service.AdminService;

import net.bytebuddy.utility.RandomString;
@Service
public class AdminLoginServiceIpml implements AdminService {
	@Autowired
	private AdminSesstionRepo adminSesstionRepo;
	
	@Autowired
	private AdminRepo adminRepo;

	@Override
	public AdminSession ragisterAdmin(Admin admin, String key) throws AdminExecption {
		String result= this.permissionTo(key);
		if(result!=null) {
			 Admin admin2= adminRepo.save(admin);
			 AdminSession loginSession=new AdminSession();
				loginSession.setAdminId(admin2.getAdminId());
				String token=RandomString.make(10);
				loginSession.setKey(token);
				loginSession.setLocalDateTime(LocalDateTime.now());
				
				return adminSesstionRepo.save(loginSession);
		}
		
	 	throw new AdminExecption("U are not the perfect person for this");
			
	}

	@Override
	public Admin deleteAdminHandller(Integer id, String key) throws AdminExecption {
		String result= this.permissionTo(key);
		if(result!=null) {
			Optional<Admin> adminOptional= adminRepo.findById(id);
			if(adminOptional.isPresent()) {
				adminRepo.delete(adminOptional.get());
				Optional<AdminSession> amdisnOptional= adminSesstionRepo.findById(adminOptional.get().getAdminId());
				adminSesstionRepo.delete(amdisnOptional.get());
				return adminOptional.get();
			}
			else {
				throw new AdminExecption("Please enter Valid Id");
			}
		}
		else {
			throw new AdminExecption("You are not the perfect person for this");
		}
	}

	@Override
	public List<Admin> getAllAdminHandller(String key) throws AdminExecption {
		String result= this.permissionTo(key);
		if(result!=null) {
			return adminRepo.findAll();
		}
		else {
			throw new AdminExecption("Yor are Not the perfect for this");
		}
	}

	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminExecption {
		AdminSession adminSession= this.permissionforAdminLogin(key);
		if(adminSession!=null) {
			 admin.setAdminId(adminSession.getAdminId());
			 return adminRepo.save(admin);
		}
		else {
			throw new AdminExecption("U are Not authorized");
		}
	}

	@Override
	public AdminSession loginAdmin(LoginDto loginDto) throws AdminExecption {
		Optional<Admin> adminOptional= adminRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
		if(adminOptional.isPresent()) {
			if(adminSesstionRepo.findById(adminOptional.get().getAdminId()).isPresent()){
				throw new AdminExecption("You are already loged in");
			}
			
			
			AdminSession loginSession=new AdminSession();
			loginSession.setAdminId(adminOptional.get().getAdminId());
			String token=RandomString.make(10);
			loginSession.setKey(token);
			loginSession.setLocalDateTime(LocalDateTime.now());
			return adminSesstionRepo.save(loginSession);
		}
		throw new AdminExecption("please Enter Valid email and passowrd");
	}

	@Override
	public AdminSession logOutAdmin(String key) throws AdminExecption {
		AdminSession adminSession= this.permissionforAdminLogin(key);
		if(adminSession!=null) {
			 adminSesstionRepo.delete(adminSession);
			 return adminSession;
		}
		
			throw new AdminExecption("U are not loged in");
		
	}
	
	
	
	//method for checking permission
	public String permissionTo(String key) {
		
	if(key.equals("hi")) {
		return "go";
	}
	return null;
	}
	
	
	//checking gor 
	public AdminSession permissionforAdminLogin(String key) {
		
		Optional<AdminSession> optional= adminSesstionRepo.findByKey(key);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}



}
