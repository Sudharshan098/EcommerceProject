package com.ecom.service;

import java.util.List;

import com.ecom.Models.Cart;
import com.ecom.customExepciton.CartExecption;


public interface CartService {

	public Cart addCart(Cart cart) throws CartExecption;
	public Cart updateCart(Cart cart) throws CartExecption; 
	public Cart DeleteCart(Integer id) throws CartExecption; // both
	public Cart getCartById(Integer id) throws CartExecption; // both
	public List<Cart>  getAllCart() throws CartExecption; // only access by admin
	
}
