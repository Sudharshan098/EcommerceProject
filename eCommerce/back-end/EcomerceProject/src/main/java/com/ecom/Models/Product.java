package com.ecom.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private Integer price;
	private Integer quantity;
	private Integer rating;
	private String desc;
	private String image;
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonBackReference
//	private Category category;
	@ManyToOne(cascade =CascadeType.ALL)
	private Category category;
	private LocalDateTime LastDateTime;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer productId,
			String productName,
			Integer price, Integer quantity, Integer rating, String desc, String image,
			LocalDateTime lastDateTime) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.rating = rating;
		this.desc = desc;
		this.image = image;
		LastDateTime = lastDateTime;
		
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public LocalDateTime getLastDateTime() {
		return LastDateTime;
	}
	public void setLastDateTime(LocalDateTime lastDateTime) {
		LastDateTime = lastDateTime;
	}


	
	

}
