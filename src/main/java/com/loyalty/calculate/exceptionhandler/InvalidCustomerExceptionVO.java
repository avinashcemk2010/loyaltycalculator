package com.loyalty.calculate.exceptionhandler;

public class InvalidCustomerExceptionVO extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidCustomerExceptionVO(String msg) {
		super(msg);
	}

}
