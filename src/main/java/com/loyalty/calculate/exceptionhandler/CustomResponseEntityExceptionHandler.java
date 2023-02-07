package com.loyalty.calculate.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.loyalty.calculate.entity.ExceptionResponse;


@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException
	(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse res =	 new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(InvalidCustomerExceptionVO.class)
	public final ResponseEntity<Object> handleInvalidCuatomerException
	(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse res =	 new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoTransactionAvailableException.class)
	public final ResponseEntity<Object> handleNoAvailableTransactionException
	(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse res =	 new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidURLException.class)
	public final ResponseEntity<Object> handleInvalidURLException
	(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse res =	 new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
	}
}
