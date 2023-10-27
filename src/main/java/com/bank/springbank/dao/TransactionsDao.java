package com.bank.springbank.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.springbank.model.Transactions;

@Repository
public interface  TransactionsDao extends JpaRepository<Transactions, UUID>{
	

	@Query("SELECT t FROM Transactions t WHERE t.sourceAccountId = ?1")
	public List<Transactions> getTransactionsForsourceAccountId( Long sourceAccountId);
}
