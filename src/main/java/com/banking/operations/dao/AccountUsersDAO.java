package com.banking.operations.dao;

import java.util.List;

import com.banking.operations.model.AccountUsers;

public interface AccountUsersDAO {
	
	public boolean isUserexists(int userid);
	public boolean createAccount(AccountUsers user);
	public boolean validateUser(String username,String password);
	public int viewBalance (String username,String password);
	public boolean withdrawAmount(String username,String password,int withdraw_amount);
	public boolean depositAmount(String username,String password,int deposit_amount);
	public boolean depositAmountByID(int senderid,int  recieverid, int deposit_amount);
	
	public boolean validateEmployee(String empname,String emppassword);
	public boolean rejectAccount(int userid);
	public boolean approveAccount(int userid);
	public List<AccountUsers> viewAllCustomers();
	public AccountUsers viewCustomersbyID(int userid);
	public List<AccountUsers> viewCustomersbyStatus(String status);
	
	
}
