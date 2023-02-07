package com.loyalty.calculate.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

	@Id
	@GeneratedValue
	private String customerId;
	private String customerName;
	private String accountnumber;
	
	@OneToMany(mappedBy = "customerDetails", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<Transactions> transactions;
	

}
