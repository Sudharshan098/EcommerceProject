package com.ecom.ModelsDto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.ecom.Models.Category;

public class ProductDto {
	private Integer productId;
	private String productName;
	private double price;
	private Integer quantity;
	private String desc;
	private String image;
	private LocalDateTime LastDateTime;

}
