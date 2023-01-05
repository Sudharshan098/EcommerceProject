package com.ecom.service;

import java.util.List;

import com.ecom.Models.Category;
import com.ecom.customExepciton.CategoryExecption;

public interface CategoryService {
	public Category createNewCategory(Category category,String key)throws CategoryExecption ;
	public Category updateCategory(Category category,String key)throws CategoryExecption;
	public Category deleteCategory(Integer id,String key) throws CategoryExecption;
	public List<Category> getAllCategories() throws CategoryExecption;
	
}
