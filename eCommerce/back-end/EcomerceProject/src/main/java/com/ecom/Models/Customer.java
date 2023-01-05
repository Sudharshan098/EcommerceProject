package com.ecom.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@Size(min = 3 ,message = "please Enter Valid Name")
	private String customerName;
	@Email
	@Column(unique = true)
	private String email;
	@Size(min = 10 ,max = 10,message = "please Enter Valid Number")
	private String mobileNo;
	@Size(min = 4,message = "please enter the Passowrd between this range min=4 and max=10")
	private String password;
	@Size(min = 4,message = "please Enter Valid Address")
	private String address;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(Integer customerId,
			@Size(min = 3, max = 10, message = "please Enter Valid Name") String customerName, @Email String email,
			@Size(min = 10, max = 10, message = "please Enter Valid Number") String mobileNo,
			@Size(min = 4, max = 10, message = "please enter the Passowrd between this range min=4 and max=10") String password,
			@Size(min = 4, message = "please Enter Valid Address") String address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
		this.address = address;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	

}
