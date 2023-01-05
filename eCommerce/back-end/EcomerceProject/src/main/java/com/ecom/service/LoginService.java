package com.ecom.service;


import com.ecom.ModelsDto.LoginDto;
import com.ecom.customExepciton.CustomerExepction;


public interface LoginService {
	public String loginUser(LoginDto loginDto) throws CustomerExepction;
	public String logOut(String key)throws CustomerExepction;

}
