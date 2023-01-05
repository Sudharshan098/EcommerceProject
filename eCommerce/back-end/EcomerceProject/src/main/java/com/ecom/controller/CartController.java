package com.ecom.controller;

import java.util.List;
import java.util.Optional;

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

import com.ecom.Models.AdminSession;
import com.ecom.Models.Cart;
import com.ecom.Models.LoginSession;
import com.ecom.Models.Product;
import com.ecom.Repo.AdminSesstionRepo;
import com.ecom.Repo.LoginSessionRepo;
import com.ecom.customExepciton.CartExecption;
import com.ecom.customExepciton.ProductExecption;
import com.ecom.service.CartService;

@RestController
@RequestMapping("/admin")
public class CartController {
	
	@Autowired
	private LoginSessionRepo loginSessionRepo;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AdminSesstionRepo adminSesstionRepo;
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> getCartByIdHandller(@PathVariable Integer id,@RequestHeader("token") String token) throws CartExecption{
		AdminSession adminSession= this.permissionforAdminLogin(token);
		 if(adminSession!=null) {
			Cart cart= cartService.getCartById(id);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		 }
		 throw new CartExecption("Please loing as a amdin");
	}
	
	@PutMapping("/cart/update")
	public ResponseEntity<Cart> updateCartHandller(@RequestBody Cart cart,@RequestHeader("token") String token) throws CartExecption{
		AdminSession adminSession= this.permissionforAdminLogin(token);
		 if(adminSession!=null) {
			Cart cart2= cartService.updateCart(cart);
			return new ResponseEntity<Cart>(cart2,HttpStatus.OK);
		 }
		 throw new CartExecption("Please loing as a amdin");
	}
	
	@DeleteMapping("/cart/delete/{id}")
	public ResponseEntity<Cart> deleteCartById(@PathVariable Integer id,@RequestHeader("token") String token) throws CartExecption{
		AdminSession adminSession= this.permissionforAdminLogin(token);
		 if(adminSession!=null) {
			 Cart cart= cartService.DeleteCart(id);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		 }
		 throw new CartExecption("Please loing as a amdin");
	}
	
	@GetMapping("/carts")
	public ResponseEntity<List<Cart>> getAllCartHandller(@RequestHeader("token") String token) throws CartExecption{
		AdminSession adminSession= this.permissionforAdminLogin(token);
		 if(adminSession!=null) {
			List<Cart> list= cartService.getAllCart();
			return new ResponseEntity<List<Cart>>(list, HttpStatus.OK);
		 }
		 throw new CartExecption("Please loing as a amdin");
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
