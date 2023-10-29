package com.bank.springbank.service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.springbank.dao.TransactionsDao;
import com.bank.springbank.model.Customer;
import com.bank.springbank.model.Status_Type;
import com.bank.springbank.model.Transaction_Type;
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
		try {
			 LocalTime localTimeNow = LocalTime.now();
			 LocalTime timeWithoutNanos = localTimeNow.withNano(0);
			 transaction.setDate(localTimeNow);
			 
			return transactionsDao.save(transaction);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public List<Transactions> getTransactionsBysourceAccountId(Long sourceAccountId) {
		return transactionsDao.getTransactionsBysourceAccountId(sourceAccountId);
	}
	
	public List<Transactions> getTransactionsBytargetAccountId(Long targetAccountId) {
		return transactionsDao.getTransactionsBytargetAccountId(targetAccountId);
	}
	
	public List<Transactions> getTransactionsByType(Transaction_Type transactionType) {
	
		return transactionsDao.getTransactionsByType(transactionType);
	}
	
	public int updateTransactionStatus(Status_Type newStatus,UUID transactionUuid) {
		return transactionsDao.updateTransactionStatus(newStatus,transactionUuid);
	}
}
