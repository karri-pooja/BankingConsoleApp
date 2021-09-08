package com.banking.operations.model;

import java.io.Serializable;

public class AccountUsers implements Serializable {

	//fields of AccountUsers class
	private int userid;
	private String username;
	private String password;
	private String gender;
	private int userage;
	private String address;
	private int balance;
	private String status;
	
	//default constructor
	public AccountUsers() {
		
	}

	//parameterized constructor
	public AccountUsers(int userid, String username, String password, String gender, int userage, String address,int balance) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.userage = userage;
		this.address = address;
		this.balance= balance;
		this.status=status;
	}

	//getters and setters for the above fields
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getUserage() {
		return userage;
	}

	public void setUserage(int userage) {
		this.userage = userage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	//to String method
	@Override
	public String toString() {
		return "AccountUsers [userid=" + userid + ", username=" + username + ", gender="
				+ gender + ", userage=" + userage + ", address=" + address + ", balance=" + balance + ", Status=" + status + "]";
	}	
	
}
