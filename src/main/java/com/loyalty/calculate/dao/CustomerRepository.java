package com.loyalty.calculate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loyalty.calculate.entity.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, String> {
	
}
