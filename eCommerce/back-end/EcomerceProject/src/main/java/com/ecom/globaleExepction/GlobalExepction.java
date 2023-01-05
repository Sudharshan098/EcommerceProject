package com.ecom.globaleExepction;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ecom.customExepciton.AdminExecption;
import com.ecom.customExepciton.CartExecption;
import com.ecom.customExepciton.CategoryExecption;
import com.ecom.customExepciton.CustomerExepction;
import com.ecom.customExepciton.ProductExecption;

@ControllerAdvice
public class GlobalExepction {
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ExecptionDetails> fileExepction(IOException e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExecptionDetails> ValidExecption(MethodArgumentNotValidException e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(ProductExecption.class)
	public ResponseEntity<ExecptionDetails> ProductExecption(ProductExecption e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(CustomerExepction.class)
	public ResponseEntity<ExecptionDetails> customerExecption(CustomerExepction e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(CategoryExecption.class)
	public ResponseEntity<ExecptionDetails> categoryExepciton(CategoryExecption e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(CartExecption.class)
	public ResponseEntity<ExecptionDetails> cartExectpion(CartExecption e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler(AdminExecption.class)
	public ResponseEntity<ExecptionDetails> adminExecpiton(AdminExecption e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExecptionDetails> mainExpetionHanller(Exception e,WebRequest webRequest){
		
		return new ResponseEntity<ExecptionDetails>(new ExecptionDetails(e.getMessage(),webRequest.getDescription(false),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
}
