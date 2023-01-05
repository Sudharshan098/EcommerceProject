package com.ecom.Models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminSession {
	@Id
	private Integer adminId;
	@Column(unique = true)
	private String key;
	private LocalDateTime localDateTime;
	public AdminSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminSession(Integer adminId, String key, LocalDateTime localDateTime) {
		super();
		this.adminId = adminId;
		this.key = key;
		this.localDateTime = localDateTime;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
}
