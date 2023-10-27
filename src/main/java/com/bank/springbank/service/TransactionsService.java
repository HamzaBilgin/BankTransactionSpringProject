package com.bank.springbank.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.springbank.dao.TransactionsDao;
import com.bank.springbank.model.Customer;
import com.bank.springbank.model.Transactions;

import lombok.Getter;
import lombok.Setter;

@Service
public class TransactionsService {

	@Autowired
	@Getter
	@Setter
	private TransactionsDao transactionsDao; 
	
	public Transactions saveTransaction(Transactions transaction) {
		 LocalTime localTimeNow = LocalTime.now();
		 LocalTime timeWithoutNanos = localTimeNow.withNano(0);
		 transaction.setDate(localTimeNow);
		 
		return transactionsDao.save(transaction);
	}
	
	public List<Transactions> getTransactionsForsourceAccountId(Long id) {
		return transactionsDao.getTransactionsForsourceAccountId(id);
	}
}
