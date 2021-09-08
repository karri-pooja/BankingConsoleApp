package com.banking.operations.exceptions;

import java.util.InputMismatchException;

public class InputMismatchException1 extends InputMismatchException {
	
	public InputMismatchException1()
	{
		System.out.println( "Enter correction input...Entered data mismatches with DB data type");
	}

}
