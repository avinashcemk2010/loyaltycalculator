package com.loyalty.calculate.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.loyalty.calculate.dao.CustomerRepository;
import com.loyalty.calculate.entity.CustomerDetails;
import com.loyalty.calculate.entity.Transactions;

@Service
public class CustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	@Value("${trace.month}")
	Integer traceMonth;

	@Value("${constant.AFTER100}")
	Float after100;

	@Value("${constant.Below100}")
	Float below100;

	@Value("${constant.AT50}")
	Float at50;

	@Value("${constant.AT100}")
	Float at100;

	public Map<String, Float> calcuateRewards(String id) {

		Date backdate = calculateBackDate();

		Optional<CustomerDetails> customerDetails = customerRepository.findById(id);
		if(customerDetails.isPresent()) {

			List<Transactions> transactions = customerDetails.get().getTransactions();
			if(transactions.size() > 0) {
				List<Transactions> validtransactions = filterByBackDate(transactions, backdate);

				Map<Integer, List<Transactions>> map = new HashMap<Integer, List<Transactions>>();
				validtransactions.forEach(trans -> {
					int month = trans.getTransactiondate().getMonth()+1;
					if(map.keySet().contains(month)) {
						map.get(month).add(trans);
					} else {
						List<Transactions> ls = new ArrayList<Transactions>();
						ls.add(trans);
						map.put(month, ls);
					}
				});

				return calculate(map);
			} 
		}

		return new HashMap<String, Float>();
	}

	private List<Transactions> filterByBackDate(List<Transactions> transactions, Date backdate) {

		return transactions.stream()
				.filter(t -> t.getTransactiondate().after(backdate))
				.collect(Collectors.toList());
	}

	public Date calculateBackDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		localDateTime = localDateTime.minusMonths(traceMonth);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

	}

	public Map<String, Float> calculate(Map<Integer, List<Transactions>> trans) {

		Map<String, Float> points = new HashMap<String, Float>();
		trans.forEach((k,v) -> {
			float f = v.stream().map(val -> val.getDebitAmount()).reduce((float) 0, (a,b) -> a+b);
			if(f > (float)100) {
				points.put(
						Month.of(k).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH), 
						(f-at100)*after100+at50*below100);
			}
		});

		Optional<Float> sum = points.values().stream().reduce(Float::sum);

		if(sum.isPresent()) {
			points.put("total", sum.get());
		} else {
			points.put("total", (float)0);
		}

		return points;

	}


}
