package com.ecom.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Models.Cart;
import com.ecom.Models.Customer;
import com.ecom.Repo.CartRepo;
import com.ecom.Repo.CustomerRepo;
import com.ecom.customExepciton.CartExecption;
import com.ecom.service.CartService;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	
	
	@Override
	public Cart addCart(Cart cart) throws CartExecption {
		Optional<Customer> cusOptiona= customerRepo.findById(cart.getCustomerId());
		
		Optional<Cart> caOptional= cartRepo.findByCustomerId(cart.getCustomerId());
		
		
		if(caOptional.isPresent()) {
			throw new CartExecption("this is user already have cart");
		}
		if(cusOptiona.isPresent()) {
			return cartRepo.save(cart);
		}
		throw new CartExecption("Please enter valid id of customer");
		
	}

	@Override
	public Cart updateCart(Cart cart) throws CartExecption {
		Optional<Cart> cartOptional= cartRepo.findById(cart.getCartId());
		if(cartOptional.isPresent()) {
			return cartRepo.save(cart);
		}
		throw new CartExecption("Cart Not found of this data");
	}

	@Override
	public Cart DeleteCart(Integer id) throws CartExecption {
		Optional<Cart> cartOptional= cartRepo.findById(id);
		if(cartOptional.isPresent()) {
			cartOptional.get().setProducts(new ArrayList<>());
			cartRepo.delete(cartOptional.get());
			return cartOptional.get();
		}
		throw new CartExecption("Cart Not found of this id");
	}

	@Override
	public Cart getCartById(Integer id) throws CartExecption {
		Optional<Cart> cartOptional= cartRepo.findById(id);
		if(cartOptional.isPresent()) {
			return cartOptional.get();
		}
		throw new CartExecption("Cart Not found of this id");
		
	}

	@Override
	public List<Cart> getAllCart() throws CartExecption {
		
		return  cartRepo.findAll();
	}

}
