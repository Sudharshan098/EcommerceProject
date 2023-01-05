package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Models.Category;
import com.ecom.customExepciton.CategoryExecption;
import com.ecom.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/admin/category/create")
	public ResponseEntity<Category> createNewCategoryHandller(@RequestBody Category category,@RequestHeader("token") String token) throws CategoryExecption{
		return new ResponseEntity<Category>(categoryService.createNewCategory(category, token), HttpStatus.CREATED);
	}
	@PutMapping("/admin/category/update")
	public ResponseEntity<Category> updateCategoryHandller(@RequestBody Category category,@RequestHeader("token") String token) throws CategoryExecption{
		return new ResponseEntity<Category>(categoryService.updateCategory(category, token), HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/admin/category/delete/{id}")
	public ResponseEntity<Category> deleteCategoryHandller(@PathVariable("id") Integer id,@RequestHeader("token") String key) throws CategoryExecption{
		return new ResponseEntity<Category>(categoryService.deleteCategory(id, key), HttpStatus.ACCEPTED);
	}
	@GetMapping("/categorys")
	public ResponseEntity<List<Category>> getAllCagoryHandller() throws CategoryExecption{
		return new ResponseEntity<List<Category>>(categoryService.getAllCategories(), HttpStatus.OK);
	}

}
