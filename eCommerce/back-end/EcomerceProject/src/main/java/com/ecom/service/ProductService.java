package com.ecom.service;

import java.util.List;

import com.ecom.Models.Product;
import com.ecom.customExepciton.ProductExecption;

public interface ProductService {
	public Product addProduct(Product product,String key) throws ProductExecption;
	public Product deleteProduct(Integer productId,String key) throws ProductExecption;
	public Product updateProduct(Product product,String key) throws ProductExecption;
	public List<Product> getAllProduct()throws ProductExecption;
	public Product findProductById(Integer id) throws ProductExecption;
	public Product findProductByName(String name) throws ProductExecption;
	
}
