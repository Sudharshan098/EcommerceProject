package com.ecom.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	private Integer customerId;
	private Integer totalItem;
	private double totalPrice;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<>();
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(Integer cartId, Integer customerId, Integer totalItem, double totalPrice, List<Product> products) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.totalItem = totalItem;
		this.totalPrice = totalPrice;
		this.products = products;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}


	
	
	

}
