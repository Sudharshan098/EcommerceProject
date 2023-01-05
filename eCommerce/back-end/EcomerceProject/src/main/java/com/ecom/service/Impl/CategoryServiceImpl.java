package com.ecom.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Models.AdminSession;
import com.ecom.Models.Category;
import com.ecom.Repo.AdminSesstionRepo;
import com.ecom.Repo.CategoryRepo;
import com.ecom.customExepciton.CategoryExecption;
import com.ecom.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private AdminSesstionRepo adminSesstionRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category createNewCategory(Category category,String key) throws CategoryExecption{
		if(this.permissionforAdminLogin(key)!=null) {
			return categoryRepo.save(category) ;
		}
		throw new CategoryExecption("Please Login as Admin");
		
	}


	@Override
	public Category updateCategory(Category category, String key) throws CategoryExecption {
		if(this.permissionforAdminLogin(key)!=null) {
		 Optional<Category> cateogryOptional=categoryRepo.findById(category.getCategoryId());
		 if(cateogryOptional.isPresent()) {
			 return categoryRepo.save(category);
		 }
		}
		throw new CategoryExecption("Please Login as Admin");
	}

	@Override
	public Category deleteCategory(Integer id, String key) throws CategoryExecption {
		if(this.permissionforAdminLogin(key)!=null) {
			 Optional<Category> cateogryOptional=categoryRepo.findById(id);
			 if(cateogryOptional.isPresent()) {
				 categoryRepo.delete(cateogryOptional.get());
				 return cateogryOptional.get();
			 }
		}
		throw new CategoryExecption("Please Login as Admin");
	}

	@Override
	public List<Category> getAllCategories() throws CategoryExecption {
		return categoryRepo.findAll();
	}
	
	// method for permsission of admin
			public AdminSession permissionforAdminLogin(String key) {
			
			Optional<AdminSession> optional= adminSesstionRepo.findByKey(key);
			if(optional.isPresent()) {
				return optional.get();
			}
			return null;
		}

}
