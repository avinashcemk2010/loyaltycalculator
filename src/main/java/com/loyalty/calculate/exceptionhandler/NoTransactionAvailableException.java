package com.loyalty.calculate.exceptionhandler;

public class NoTransactionAvailableException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NoTransactionAvailableException(String msg) {
		super(msg);
	}

}
