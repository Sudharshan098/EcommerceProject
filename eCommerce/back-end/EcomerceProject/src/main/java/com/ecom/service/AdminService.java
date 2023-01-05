package com.ecom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecom.Models.Admin;
import com.ecom.Models.AdminSession;
import com.ecom.ModelsDto.LoginDto;
import com.ecom.customExepciton.AdminExecption;

public interface AdminService {
	
	public AdminSession ragisterAdmin(Admin admin,String key)throws AdminExecption;
	public Admin deleteAdminHandller(Integer id,String key) throws AdminExecption;
	public List<Admin> getAllAdminHandller(String key) throws AdminExecption;
	public Admin updateAdmin(Admin admin,String key) throws AdminExecption;
	public AdminSession loginAdmin(LoginDto loginDto) throws AdminExecption;
	public AdminSession logOutAdmin(String key) throws AdminExecption;

}
