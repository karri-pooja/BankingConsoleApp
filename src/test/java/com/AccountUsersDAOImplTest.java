package com;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banking.operations.dao.AccountUsersDAO;
import com.banking.operations.dao.AccountUsersDAOImpl;
import com.banking.operations.model.AccountUsers;

public class AccountUsersDAOImplTest {
	
	private AccountUsersDAO accountusersDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		accountusersDAO=new AccountUsersDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		accountusersDAO=null;
	}
	
	@Test
	public void testCreateAccount() 
	{
		int customerIdToTest = -999;
		
		List<AccountUsers> originalCustomer1 =accountusersDAO.viewAllCustomers();
		
		accountusersDAO.createAccount(new AccountUsers(customerIdToTest,"RENU","renu@35","female",22,"mdp",500));
		
		List<AccountUsers> originalCustomer2 = accountusersDAO.viewAllCustomers();
		
		assertEquals(originalCustomer2.size(), originalCustomer1.size() + 1);
		
		accountusersDAO.rejectAccount(customerIdToTest);	
	}
	
	@Test
	public void testValidateCustomer() {
		
		int customerIdToTest = -999;
		
		String username="pooja";
		
		String password="pooja@40";
		
		List<AccountUsers> originalCustomer1 =accountusersDAO.viewAllCustomers();
		
		accountusersDAO.createAccount(new AccountUsers(customerIdToTest,username,password,"female",22,"mdp",500));
		
		boolean result1=true;
		
		boolean result2;
		
		result2=accountusersDAO.validateUser(username, password);
		
		assertEquals(result1, result2);
	}
	
	
	
	@Test
	public void testViewBalance() 
	{
		
		int startingBalance=5000;
		
		int customerIdToTest = -999;
		
		accountusersDAO.createAccount(new AccountUsers(customerIdToTest,"RENU","renu@35","female",22,"mdp",startingBalance));
		
		int retrievedBalance=accountusersDAO.viewBalance("RENU","renu@35");
		
		assertEquals(startingBalance, retrievedBalance);
		
		accountusersDAO.rejectAccount(customerIdToTest);
	}
	
	@Test
	public void testWithdraw() {
		
		int startingBalance=5000;
		
		int balanceToWithdraw=500;
		
		int customerIdToTest = -999;
		
		int oldBalance = startingBalance-balanceToWithdraw;
		
		String username="pooja";
		
		String password="pooja@40";
		
		accountusersDAO.createAccount(new AccountUsers(customerIdToTest,username,password,"female",22,"mdp",startingBalance));
		
		accountusersDAO.withdrawAmount(username,password,balanceToWithdraw);
		
		int newBalance=accountusersDAO.viewBalance(username,password);
		
		assertEquals(oldBalance, newBalance);
		
		accountusersDAO.rejectAccount(customerIdToTest);
	}
	
	
	@Test
	public void testDeposit() {
		
		int startingBalance=5000;
		
		int balanceToDeposit=500;
		
		int customerIdToTest = -999;
		
		int oldBalance = startingBalance+balanceToDeposit;
		
		String username="pooja";
		
		String password="pooja@40";
		
		accountusersDAO.createAccount(new AccountUsers(customerIdToTest,username,password,"female",22,"mdp",startingBalance));
		
		accountusersDAO.depositAmount(username,password,balanceToDeposit);
		
		int newBalance=accountusersDAO.viewBalance(username,password);
		
		assertEquals(oldBalance, newBalance);
		
		accountusersDAO.rejectAccount(customerIdToTest);
	}
	
	
}
