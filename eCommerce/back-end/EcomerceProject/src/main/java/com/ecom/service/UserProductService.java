package com.ecom.service;

import java.util.List;
import java.util.Set;

import com.ecom.Models.Cart;
import com.ecom.Models.Product;
import com.ecom.customExepciton.CartExecption;
import com.ecom.customExepciton.ProductExecption;

public interface UserProductService {
	public Cart addProduct(Product product,String key) throws ProductExecption ,CartExecption;
	public Cart deleteProduct(Integer productId,String key) throws ProductExecption,CartExecption;
//	public Cart updateProduct(Product product,String key) throws ProductExecption,CartExecption;
	public List<Product> getAllProduct()throws ProductExecption;
	public Product findProductById(Integer id) throws ProductExecption;
	public List<Product> findProductByName(String name) throws ProductExecption;
	public List<Product> sortByRating(Integer startingPoint ,Integer endingPoint) throws ProductExecption;
	public Set<Product> searchByCatgory(String categoryName) throws ProductExecption;
}
