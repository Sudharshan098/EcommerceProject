package com.ecom.globaleExepction;

import java.time.LocalDate;

public class ExecptionDetails {
	private String name;
	private String des;
	private LocalDate localDate;
	public ExecptionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExecptionDetails(String name, String des, LocalDate localDate) {
		super();
		this.name = name;
		this.des = des;
		this.localDate = localDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	

}
