package com.ecom.controller;

import java.util.List;

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

import com.ecom.Models.Admin;
import com.ecom.Models.AdminSession;
import com.ecom.ModelsDto.LoginDto;
import com.ecom.customExepciton.AdminExecption;
import com.ecom.service.AdminService;
import com.ecom.service.Impl.AdminLoginServiceIpml;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/ragister")
	public ResponseEntity<AdminSession> ragisterAdmin(@RequestBody Admin admin,@RequestHeader("token") String token) throws AdminExecption{
		AdminSession adminSession= adminService.ragisterAdmin(admin, token);
		return new ResponseEntity<AdminSession>(adminSession, HttpStatus.CREATED);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Admin> deleteAdminHandller(@PathVariable Integer id,@RequestHeader("token") String token) throws AdminExecption{
		Admin admin= adminService.deleteAdminHandller(id, token);
		return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
	}
	@GetMapping("/allAdmin")
	public ResponseEntity<List<Admin>> getAllAdminHandller(@RequestHeader("token") String token) throws AdminExecption{
		List<Admin> admins= adminService.getAllAdminHandller(token);
		return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);
	}
	@PutMapping("/udateAdmin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin,@RequestHeader("token") String token) throws AdminExecption{
		Admin admin2= adminService.updateAdmin(admin, token);
		return new ResponseEntity<Admin>(admin2, HttpStatus.OK);
		
	}
	@PostMapping("/login")
	public ResponseEntity<AdminSession> loginAdmin(@RequestBody LoginDto loginDto) throws AdminExecption{
		AdminSession adminSession= adminService.loginAdmin(loginDto);
		return new ResponseEntity<AdminSession>(adminSession, HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("/logout")
	public ResponseEntity<AdminSession> logOutAdmin(@RequestHeader("token") String token) throws AdminExecption{

		AdminSession adminSession= adminService.logOutAdmin(token);
		return new ResponseEntity<AdminSession>(adminSession, HttpStatus.ACCEPTED);
	}

}
