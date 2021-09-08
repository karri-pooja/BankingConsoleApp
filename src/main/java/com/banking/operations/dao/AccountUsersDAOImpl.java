package com.banking.operations.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banking.operations.model.AccountUsers;
import com.banking.operations.util.DBConnection;

//implementing AccountUsersDAO interface
public class AccountUsersDAOImpl implements AccountUsersDAO {
	
	Connection connection= DBConnection.getDBConnection();
	
	//queries 
	public final String ADD_USER="insert into hr.AccountUsers values(?,?,?,?,?,?,?)";
	public final String VALIDATE_USER="select * from hr.AccountUsers where username=? and password=?";
	public final String VIEW_BALANCE="select balance from hr.AccountUsers where username=? and password=?";
	public final String WITHDRAW_AMOUNT="update hr.AccountUsers set balance=balance-? where username=? and password=?";
	public final String DEPOSIT_AMOUNT="update hr.AccountUsers set balance=balance+? where username=? and password=?";
	
	public final String VALIDATE_EMPLOYEE = "select * from hr.BankEmployee where empname=? and emppassword=?";
	public final String VIEW_ALL_CUSTOMERS="select * from hr.AccountUsers";
	public final String VIEW_CUSTOMERS_BY_ID="select * from hr.AccountUsers where userid=?";
	public final String VIEW_CUSTOMERS_BY_STATUS="select * from hr.AccountUsers where status=?";
	public final String APPROVE_ACCOUNT="update hr.AccountUsers set status='Approved' where userid=?";
	public final String REJECT_ACCOUNT="delete from hr.AccountUsers where userid=?";
	
	
	

	public boolean createAccount(AccountUsers user) {
		int res=0;
		try {
			//adding values to the table
			PreparedStatement statement= connection.prepareStatement(ADD_USER);
			statement.setInt(1,user.getUserid());
			statement.setString(2,user.getUsername());
			statement.setString(3,user.getPassword());
			statement.setString(4,user.getGender());
			statement.setInt(5,user.getUserage());
			statement.setString(6,user.getAddress());
			statement.setInt(7,user.getBalance());
			res=statement.executeUpdate();
			if(res==0) 
				//if new row is not inserted
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if new row is inserted
		return true;	
	}
	
	

	public boolean validateUser(String username, String password) {
		PreparedStatement statement;
		try {
			//validating the user
			statement = connection.prepareStatement(VALIDATE_USER);
			statement.setString(1,username);
			statement.setString(2,password);
			ResultSet res=statement.executeQuery();
			if(res.next()) 
				//user is valid
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//user is invalid
		return false;
	}

	
	
	
	public int viewBalance(String username, String password) {
		PreparedStatement statement;
		ResultSet res;
		try {	
			//query for retrieving the balance
			statement = connection.prepareStatement(VIEW_BALANCE);
			statement.setString(1,username);
			statement.setString(2,password);
			res=statement.executeQuery();
			if(res.next())
				//displays balance when user name and password is correct
				return res.getInt(1);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// when user name and password is incorrect
		return 0;
	}

	
	
	
	public boolean withdrawAmount(String username, String password,int withdraw_amount) {	
		PreparedStatement statement;
		int res=0;
		try {	
			//validating the user
			statement = connection.prepareStatement(WITHDRAW_AMOUNT);
			statement.setInt(1,withdraw_amount);
			statement.setString(2,username);
			statement.setString(3,password);
			res=statement.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res==0) 
			//withdraw failed
			return false;
		else 
			//withdraw successful
			return true;
	}

	
	
	
	public boolean depositAmount(String username, String password,int deposit_amount) {	
		PreparedStatement statement;
		try {	
			//validating the user
			statement = connection.prepareStatement(DEPOSIT_AMOUNT);
			statement.setInt(1,deposit_amount);
			statement.setString(2,username);
			statement.setString(3,password);
			int res=statement.executeUpdate();
			if(res==0) 
				//deposit failed
				return false;
			else 	
				//deposit successful
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	public List<AccountUsers> viewAllCustomers() {	
		List<AccountUsers> accountUsers =new ArrayList<AccountUsers>();
		PreparedStatement statement;	
		try {		
			statement =connection.prepareStatement(VIEW_ALL_CUSTOMERS);
			ResultSet res= statement.executeQuery();			
			while(res.next())
			{
				AccountUsers accountUser = new AccountUsers();
				//adding fields to the array
				accountUser.setUserid(res.getInt(1));
				accountUser.setUsername(res.getString(2));
				accountUser.setPassword(res.getString(3));
				accountUser.setGender(res.getString(4));
				accountUser.setUserage(res.getInt(5));
				accountUser.setAddress(res.getString(6));
				accountUser.setBalance(res.getInt(7));			
				//add users to Account users list
				accountUsers.add(accountUser);
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return accountUsers;
	}
	
	
	

	public AccountUsers viewCustomersbyID(int userid) {
		AccountUsers accountUser = new AccountUsers();
		PreparedStatement statement;	
		try {	
			statement =connection.prepareStatement(VIEW_CUSTOMERS_BY_ID);
			statement.setInt(1, userid);
			ResultSet res= statement.executeQuery();
			while(res.next())
			{
				//adding fields to the array
				accountUser.setUserid(res.getInt(1));
				accountUser.setUsername(res.getString(2));
				accountUser.setPassword(res.getString(3));
				accountUser.setGender(res.getString(4));
				accountUser.setUserage(res.getInt(5));
				accountUser.setAddress(res.getString(6));
				accountUser.setBalance(res.getInt(7));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return accountUser;
	}
	
	
	
	public List<AccountUsers> viewCustomersbyStatus(String status) {
		List<AccountUsers> accountUsers =new ArrayList<AccountUsers>();
		PreparedStatement statement;
		try {	
			statement =connection.prepareStatement(VIEW_CUSTOMERS_BY_STATUS);
			statement.setString(1, status);
			ResultSet res= statement.executeQuery();
			while(res.next())
			{
				AccountUsers accountUser = new AccountUsers();
				//adding fields to the array
				accountUser.setUserid(res.getInt(1));
				accountUser.setUsername(res.getString(2));
				accountUser.setPassword(res.getString(3));
				accountUser.setGender(res.getString(4));
				accountUser.setUserage(res.getInt(5));
				accountUser.setAddress(res.getString(6));
				accountUser.setBalance(res.getInt(7));
				//add users to Account users list
				accountUsers.add(accountUser);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return accountUsers;	
	}

	
	

	public boolean depositAmountByID(int senderid,int  recieverid,int deposit_amount) {
		PreparedStatement statement;
		PreparedStatement statement1;
		int res=0,res1=0;
		try {	
			//To withdraw amount from sender
			statement = connection.prepareStatement("update hr.AccountUsers set balance = balance-? where userid=?");
			statement.setInt(1, deposit_amount);
			statement.setInt(2, senderid);
			//To deposit the withdraw amount to receiver
			statement1 = connection.prepareStatement("update hr.AccountUsers set balance = balance+? where userid=?");
			statement1.setInt(2, recieverid);
			statement1.setInt(1, deposit_amount);
		    res= statement.executeUpdate();
		    res1= statement1.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res==0 && res1==0) 	
			//deposit failed
			return false;
		else 	
			//deposit successful
			return true;
	}
	
	
	
	
	public boolean validateEmployee(String empname,String emppassword) {	
		PreparedStatement statement;
		try {	
			//validating the user
			statement = connection.prepareStatement(VALIDATE_EMPLOYEE);
			statement.setString(1,empname);
			statement.setString(2,emppassword);
			ResultSet res=statement.executeQuery();
			if(res.next()) 
				//Employee is valid
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//user is invalid
		return false;
	}
	
	
	

	public boolean rejectAccount(int userid) {
		PreparedStatement statement;
		try {	
			//validating the user
			statement = connection.prepareStatement(REJECT_ACCOUNT);
			statement.setInt(1,userid);
			int res=statement.executeUpdate();
			if(res==0) 
				//user is valid
				return false;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//user is invalid
		return true;
	}

	
	
	
	
	public boolean approveAccount(int userid) {
		PreparedStatement statement;
		try {	
			//validating the user
			statement = connection.prepareStatement(APPROVE_ACCOUNT);
			statement.setInt(1,userid);
			int res=statement.executeUpdate();
			if(res==0) 
				//user is valid
				return false;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//user is invalid
		return true;
	}

	
	
	
	
	public boolean isUserexists(int userid) {
		boolean result = false;
		try {
			PreparedStatement stat = connection.prepareStatement(VIEW_CUSTOMERS_BY_ID);
			stat.setInt(1, userid);
			ResultSet res = stat.executeQuery();

			if (res.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}

