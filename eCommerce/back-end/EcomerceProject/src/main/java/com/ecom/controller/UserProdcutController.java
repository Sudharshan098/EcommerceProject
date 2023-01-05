package com.ecom.controller;

import java.util.List;
import java.util.Set;

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

import com.ecom.Models.Cart;
import com.ecom.Models.Product;
import com.ecom.customExepciton.CartExecption;
import com.ecom.customExepciton.ProductExecption;
import com.ecom.service.UserProductService;

@RestController
@RequestMapping("/user")
public class UserProdcutController {
	@Autowired
	private UserProductService userProductService;
	
	@PostMapping("/product/add")
	public ResponseEntity<Cart> addProductToCart(@RequestBody Product product,@RequestHeader("token") String key) throws ProductExecption, CartExecption{
		
		Cart cart= userProductService.addProduct(product, key);
		return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
	}
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<Cart> deleteProductInCartHandller(@PathVariable("id") Integer id,@RequestHeader("token") String key) throws ProductExecption, CartExecption{
		Cart cart= userProductService.deleteProduct(id, key);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProductHandller() throws ProductExecption{
		List<Product> list= userProductService.getAllProduct();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) throws ProductExecption{
		Product product= userProductService.findProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/products/name/{name}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) throws ProductExecption{
	  List<Product> listProducts=userProductService.findProductByName(name);
	  return new ResponseEntity<List<Product>>(listProducts, HttpStatus.OK);
	}
	@GetMapping("/products/ratingrange/{startpoint}/{endpoint}")
	public ResponseEntity<List<Product>> sortByRating(@PathVariable("startpoint") Integer startpoint,Integer endpoint) throws ProductExecption{
		List<Product> list= userProductService.sortByRating(endpoint, endpoint);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	@GetMapping("/product/categoryName/{name}")
	public ResponseEntity<Set<Product>> getProductsByCategory(@PathVariable("name") String name) throws ProductExecption{
		Set<Product> products= userProductService.searchByCatgory(name);
		return new ResponseEntity<Set<Product>>(products, HttpStatus.OK);
	}
	
}
