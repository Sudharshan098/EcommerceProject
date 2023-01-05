package com.ecom.Models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
@Entity
public class LoginSession {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer loginId;
	@Column(unique = true)
	private String key;
	private LocalDateTime dateTime;
	public LoginSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginSession(Integer customerId, String key, LocalDateTime dateTime) {
		super();
		this.loginId = customerId;
		this.key = key;
		this.dateTime = dateTime;
	}
	public Integer getCustomerId() {
		return loginId;
	}
	public void setCustomerId(Integer customerId) {
		this.loginId = customerId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	

}
