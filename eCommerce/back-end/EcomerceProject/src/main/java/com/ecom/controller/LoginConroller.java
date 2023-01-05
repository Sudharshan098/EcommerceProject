package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Models.LoginSession;
import com.ecom.ModelsDto.LoginDto;
import com.ecom.ModelsDto.LoginKeyDto;
import com.ecom.customExepciton.CustomerExepction;
import com.ecom.service.LoginService;

@RestController
public class LoginConroller {
	@Autowired
	private LoginService loginService;
	@PostMapping("/customer/login")
	public ResponseEntity<LoginKeyDto> loginHandller(@RequestBody LoginDto loginDto) throws CustomerExepction{
		System.out.println("ehllo");
		String valueString= loginService.loginUser(loginDto);
		LoginKeyDto loginKeyDto=new LoginKeyDto(valueString);
		return new ResponseEntity<LoginKeyDto>(loginKeyDto, HttpStatus.OK);
	}
	@GetMapping("/customer/logout/{key}")
	public ResponseEntity<String> logoutHandller(@PathVariable String key) throws CustomerExepction{
	 String value=loginService.logOut(key);
	 return new ResponseEntity<String>(value, HttpStatus.OK);
	}
	
	

}
