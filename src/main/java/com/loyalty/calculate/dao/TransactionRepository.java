package com.loyalty.calculate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loyalty.calculate.entity.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, String> {
	

}
