package com.ecom.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecom.Models.AdminSession;
import com.ecom.Models.Product;
import com.ecom.Repo.AdminSesstionRepo;
import com.ecom.customExepciton.ProductExecption;

@Component
public class FileUploadServiceImpl {
	@Autowired
	private AdminSesstionRepo adminSesstionRepo;
	
	public String fileUpload(MultipartFile file,String token) throws IOException, ProductExecption {
		
		
		AdminSession adminSession= this.permissionforAdminLogin(token);
		 if(adminSession!=null) {
				String pathName=new ClassPathResource("").getFile().getAbsolutePath();
				Files.copy(file.getInputStream(),Paths.get(pathName+"/static/images"+File.separator+file.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
//				String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString();
				return "file uploaded successfully";
		 }
		 throw new ProductExecption("Please loing as a amdin");
		 
	}
	// method for permission 
	public AdminSession permissionforAdminLogin(String key) {
		
		Optional<AdminSession> optional= adminSesstionRepo.findByKey(key);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
