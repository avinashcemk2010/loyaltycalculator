package com.loyalty.calculate.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyalty.calculate.exceptionhandler.InvalidCustomerExceptionVO;
import com.loyalty.calculate.exceptionhandler.InvalidURLException;
import com.loyalty.calculate.exceptionhandler.NoTransactionAvailableException;
import com.loyalty.calculate.service.CustomerService;

@RestController
@RequestMapping("/api/v1/loyalty")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Value("${constant.total}")
	String total;
	
	@GetMapping("/points/{id}")
	public ResponseEntity<Map<String, Float>> getCustomerLoyaltyPoints(@PathVariable String id) throws Exception {
		
		Optional<String> custid = Optional.ofNullable(id);
		if(custid.isEmpty()) 
			throw new InvalidURLException("Customer id might be missing in path");
		
		Map<String, Float> points = customerService.calcuateRewards(id);
		
		if(points.containsKey(total) && points.keySet().size() > 1) {
			return new ResponseEntity<Map<String, Float>>(points, HttpStatus.OK);
		} if(points.containsKey(total) && points.keySet().size() == 1) {
			throw new NoTransactionAvailableException("No Transaction is available for the customer");
		} else {
			throw new InvalidCustomerExceptionVO("Customer is either invalid or not present in list");
		}
	}
}
