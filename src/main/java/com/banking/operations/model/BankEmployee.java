package com.banking.operations.model;

import java.io.Serializable;

public class BankEmployee implements Serializable{
	
	private String empname;
	private String emppassword;
	
	
	public BankEmployee(String empname, String emppassword) {
		super();
		this.empname = empname;
		this.emppassword = emppassword;
	}
	
	
	public String getEmpname() {
		return empname;
	}


	public void setEmpname(String empname) {
		this.empname = empname;
	}


	public String getEmppassword() {
		return emppassword;
	}


	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
	}

}
