package com.ecom.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Models.AdminSession;
import com.ecom.Models.Category;
import com.ecom.Models.Product;
import com.ecom.Repo.AdminSesstionRepo;
import com.ecom.Repo.CategoryRepo;
import com.ecom.Repo.ProductRepo;
import com.ecom.customExepciton.ProductExecption;
import com.ecom.service.ProductService;
@Service
//@Transactional
public class AdminProductImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private AdminSesstionRepo adminSesstionRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Product addProduct(Product product,String key) throws ProductExecption {
		 AdminSession adminSession= this.permissionforAdminLogin(key);
		 if(adminSession!=null) {
			Optional<Category> categorOptional= categoryRepo.findByCategoryName(product.getCategory().getCategoryName());
			if(categorOptional.isPresent()) {
//				Set<Product> list=categorOptional.get().getProducts();
//				list.add(product);
//				Category category= categoryRepo.save(categorOptional.get());
//				Set<Product> liSet=category.getProducts();
//				Product product2=null;
//				for(Product i:liSet) {
//					product2=i;
//				}
				product.setCategory(categorOptional.get());
				return productRepo.save(product);
				
			}
			throw new ProductExecption("Category Not found ");
		 }
		 throw new ProductExecption("Please login As admin");
		 
	}

	@Override
	public Product deleteProduct(Integer productId,String key) throws ProductExecption {
		 AdminSession adminSession= this.permissionforAdminLogin(key);
		 if(adminSession!=null) {
			 Optional<Product> productOptional= productRepo.findById(productId);
				if(productOptional.isPresent()) {
					productRepo.delete(productOptional.get());
					return productOptional.get();
				}
				throw new ProductExecption("Product Id Not found");
		 }
		 throw new ProductExecption("Please login as a admin");
	}

	@Override
	public Product updateProduct(Product product,String key) throws ProductExecption {
		AdminSession adminSession= this.permissionforAdminLogin(key);
		 if(adminSession!=null) {
			 Optional<Product> productOptional= productRepo.findById(product.getProductId());
				if(productOptional.isPresent() && productOptional.get().getCategory().getCategoryId()==product.getCategory().getCategoryId()) {
					
					return productRepo.save(product);
				}
				throw new ProductExecption("Product Id Not found");
		 }
		 throw new ProductExecption("Please loing as a amdin");
	}

	@Override
	public List<Product> getAllProduct() throws ProductExecption {
		
		return  productRepo.findAll();
	}

	@Override
	public Product findProductById(Integer id) throws ProductExecption {
		
		return null;
	}

	@Override
	public Product findProductByName(String name) throws ProductExecption {
		// TODO Auto-generated method stub
		return null;
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
