package com.ecom.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecom.Models.Product;
import com.ecom.ModelsDto.LoginKeyDto;
import com.ecom.customExepciton.ProductExecption;
import com.ecom.service.Impl.AdminProductImpl;
import com.ecom.service.Impl.FileUploadServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminProductController {
	@Autowired
	private FileUploadServiceImpl fileUploadServiceImpl;
	@Autowired
	private AdminProductImpl adminProductImpl;
	private static String file;

	@PostMapping("/product/file")
	public ResponseEntity<LoginKeyDto> fileUploadingHandller(@RequestParam("file") MultipartFile file,@RequestHeader("token") String token) throws ProductExecption, IOException{
		if (file.isEmpty()) {
			throw new ProductExecption("file is empaty");
		}
		this.file=file.getOriginalFilename();
		fileUploadServiceImpl.fileUpload(file, token);
		LoginKeyDto loginKeyDto=new LoginKeyDto("file uploaded sucessfully");
		return new ResponseEntity<LoginKeyDto>(loginKeyDto, HttpStatus.CREATED);
		
		
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProducthandller(@RequestHeader("token") String token ,@RequestBody Product product) throws ProductExecption{
		if(file!=null) {
			
			product.setLastDateTime(LocalDateTime.now());
			Product product2= adminProductImpl.addProduct(product,token);
			return new ResponseEntity<Product>(product2, HttpStatus.CREATED);
		}
		throw new ProductExecption("file not found");
	
	}
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<Product> deleteProductHandlller(@RequestHeader("token") String key,@PathVariable Integer id) throws ProductExecption{
		Product product= adminProductImpl.deleteProduct(id, key);
		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/product/update")
	public ResponseEntity<Product> updateProductHandller(@RequestHeader("token") String key,@RequestBody Product product) throws ProductExecption{
		if(file!=null) {
			product.setImage(file);
		}
		Product product2= adminProductImpl.updateProduct(product, key);
		return new ResponseEntity<Product>(product2, HttpStatus.OK);
	}
	
	
	
}
