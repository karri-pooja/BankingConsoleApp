package com;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.*;

import com.banking.operations.dao.AccountUsersDAO;
import com.banking.operations.dao.AccountUsersDAOImpl;
import com.banking.operations.exceptions.InputMismatchException1;
import com.banking.operations.model.AccountUsers;

public class BankingApp {
	
	int choice=0;
	Scanner sc=new Scanner(System.in);
	private static Logger logger=Logger.getLogger("Banking App");
	
	AccountUsersDAO accountusersDAO = new AccountUsersDAOImpl();
	boolean result;
	AccountUsers accountusers = new AccountUsers();
	
	public void startBankingApp() {
		
		try {
		logger.info(" Account User App Started ");
		System.out.println("Are you :");
		System.out.println("1. Employee of the bank :");
		System.out.println("2. Customer :");
		choice = sc.nextInt();
		if(choice==2)
		{
		  while(true)
		  {
			System.out.println("----------MENU----------");
			System.out.println("1. Register for new customer account");
			System.out.println("2. Login to customer account");
			System.out.println("3. EXIT");
			
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:
				logger.info("Welcome...! To the Registration Page");
				accountusers= acceptUserDetails();
				if(accountusersDAO.isUserexists(accountusers.getUserid()))
				{
					System.out.println("User with User ID : " + accountusers.getUserid() + " already  exists");
				}
				else {
					result = accountusersDAO.createAccount(accountusers);
					if (result)
						System.out.println("Customer Account for : " + accountusers.getUsername() + " is successful");
					else
						System.out.println("Customer Account for : " + accountusers.getUsername()+ "is  Not Done");
				}
				break;
			case 2 :
				logger.info("Welcome...! To the Login Page");
				System.out.println("User Name");
				String username=sc.next();
				System.out.println("Password");
				String password=sc.next();
				boolean result = accountusersDAO.validateUser(username,password);
				
				if(result)
				{
				  while(true)
				  {
					System.out.println("Welcome " + username);
					System.out.println("Enter your choice");
					System.out.println("1. view account balance");
					System.out.println("2. Withdraw from my account");
					System.out.println("3. Deposit to my Account");
					System.out.println("4. Deposit Amount to another Account");
					System.out.println("5. EXIT");
					choice = sc.nextInt();
					switch(choice)
					{
					case 1:
						logger.info("Check Account Balance");
						int balance = accountusersDAO.viewBalance(username,password);
						System.out.println("Account Balance of " + accountusers.getUsername() + " is " + balance);
						break;
						
					case 2:
						logger.info("Welcome...! To WithDraw Amount");
						System.out.println("Enter Amount to withdraw");
						int amount= sc.nextInt();
						if(amount<0)
						{
							System.out.println("WithDraw amount should not be negative");					
						}
						else if(amount > accountusersDAO.viewBalance(username,password))
						{
							System.out.println("Sufficient Balance is Not available to WithDraw Amount");
							System.out.println("Available balance is " + accountusersDAO.viewBalance(username,password));
						}
						else {
							boolean result1 = accountusersDAO.withdrawAmount(username,password,amount);
							if(result1)
							{
								int balance1 = accountusersDAO.viewBalance(username,password);
								System.out.println("New Account Balance of " + accountusers.getUsername() + " is " + balance1);
							}
							else
							{
								System.out.println("Withdrawl fail");
							}
						}
						break;
					case 3 :
						logger.info("Welcome...! To Deposit Amount");
						System.out.println("Enter Amount to Deposit");
						int amount1= sc.nextInt();
						boolean result2 = accountusersDAO.depositAmount(username,password,amount1);
						if(result2)
						{
							int balance1 = accountusersDAO.viewBalance(username,password);
							System.out.println("New Account Balance of " + accountusers.getUsername() + " is " + balance1);
						}
						else
						{
							System.out.println("Deposit fail");
						}
						break;
					case 4:
						logger.info("Welcome...! To Deposit Amount To Another Account");
						System.out.println("Enter Your User ID");
						int senderid= sc.nextInt();
						System.out.println("Enter Recepients User ID");
						int recepid= sc.nextInt();
						System.out.println("Enter Amount to Deposit");
						int amount2= sc.nextInt();
						boolean result3 = accountusersDAO.depositAmountByID(senderid,recepid,amount2);
						if(result3)
						{
							int balance1 = accountusersDAO.viewBalance(username,password);
							System.out.println("New Account Balance of " + accountusers.getUsername() + " is " + balance1);
						}
						else
						{
							System.out.println("Deposit fail");
						}
						break;
					case 5:
						logger.info("Thanks for using the App");
						System.exit(0);
					default : 
						System.out.println("Enter correct input");
					}
					
					
				  }
				  
				}
				else
				{
					logger.info("Invalid User name/password");
					
				}
				break;
			case 3 : 
				logger.info("Thanks for using the App");
				System.exit(0);
				
			}
		  }
		}
		else if(choice==1){
			
			System.out.println("Welcome...! To the Employees Login Page");
			System.out.println("Employee Name");
			String empname=sc.next();
			System.out.println("Password");
			String password=sc.next();
			boolean result = accountusersDAO.validateEmployee(empname,password);
			if(result)
			{
			  while(true)
			  {
				System.out.println("Welcome " + empname);
				System.out.println("Enter your choice");
				System.out.println("1. View All Customer Details");
				System.out.println("2. View Customer Details By ID");
				System.out.println("3. Approve Customer Account");
				System.out.println("4. Reject Customer Account");
				System.out.println("5. EXIT");
				choice = sc.nextInt();
				switch(choice)
				{
				case 1: logger.info("Welcome To Customer Details Page");
						List<AccountUsers> accountUsers = accountusersDAO.viewAllCustomers();
						System.out.println("-----All Customer Details-----");
						Iterator<AccountUsers> iter= accountUsers.iterator();
						while(iter.hasNext())
						{
							System.out.println(iter.next());
						}
						break;
				case 2: logger.info("Get Customer Details By Customer ID");
						System.out.println("Enter Customer ID");
						int userid= sc.nextInt();
						AccountUsers accountUser1 = accountusersDAO.viewCustomersbyID(userid);
						System.out.println("----------------------");
						System.out.println(accountUser1);
						break;
				case 3 : logger.info("Approving User Account");
						String pending= "pending" ;
						List<AccountUsers> accountUsers1 = accountusersDAO.viewCustomersbyStatus(pending);
						System.out.println("-----All Customer Details with pending Status-----");
						Iterator<AccountUsers> iter1= accountUsers1.iterator();
						while(iter1.hasNext())
						{
							System.out.println(iter1.next());
						}
						System.out.println(" Enter UserID to Approve account");
						int userId=sc.nextInt();
						Boolean result1 = accountusersDAO.approveAccount(userId);
						if(result1)
						{
							System.out.println("User Account with user ID : " + userId + " has Approved");
						}
						else
							System.out.println("Approval Failed");
						break;
				case 4: logger.info("Rejecting User Account");
						System.out.println("----------------------------------------");
						System.out.println(" Enter UserID to Reject account");
						int userId1=sc.nextInt();
						Boolean result2 = accountusersDAO.rejectAccount(userId1);
						if(result2)
						{
							System.out.println("User Account with user ID : " + userId1 + " has Rejected");
						}
						else
							System.out.println("Approval Failed");
				case 5:
					logger.info("Thanks for using the App");
					System.exit(0);
						
				}
			  }
			}
			else {
				System.out.println("Invalid Empname/password");
			}
		}
		else
			System.out.println("Enter correct input");
		}
		catch(InputMismatchException e)
		  {
			  throw new  InputMismatchException1();
		  }
		
	}

	private AccountUsers acceptUserDetails() {
	  try {
		System.out.println("User ID");
		int userid=sc.nextInt();
		System.out.println("User Name");
		String username=sc.next();
		System.out.println("Password");
		String password=sc.next();
		System.out.println("Gender");
		String gender=sc.next();
		System.out.println("User Age");
		int age=sc.nextInt();
		System.out.println("User Address");
		String address=sc.next();
		System.out.println("Balance");
		int balance=sc.nextInt();
		
		AccountUsers accountusers = new AccountUsers(userid,username,password,gender,age,address,balance); 
		return accountusers;
	  }
	  catch(InputMismatchException e)
	  {
		  throw new  InputMismatchException1();
	  }
	}
	

}
