package com.bank.springbank.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bank.springbank.model.Status_Type;
import com.bank.springbank.model.Transaction_Type;
import com.bank.springbank.model.Transactions;

@Repository
public interface  TransactionsDao extends JpaRepository<Transactions, UUID>{
	

	@Query("SELECT t FROM Transactions t WHERE t.sourceAccountId = ?1")
	public List<Transactions> getTransactionsBysourceAccountId( Long sourceAccountId);
	
	@Query("SELECT t FROM Transactions t WHERE t.targetAccountId = ?1")
	public List<Transactions> getTransactionsBytargetAccountId( Long targetAccountId);
		
	@Query("SELECT t FROM Transactions t WHERE t.transactionType = :transactionTypeParameter")
	public List<Transactions> getTransactionsByType(@Param("transactionTypeParameter") Transaction_Type transactionType);
	
	 @Transactional
	    @Modifying
	@Query("UPDATE Transactions SET status = :newStatusParameter WHERE transactionUuid = :transactionUuidParameter")
	public int updateTransactionStatus(@Param("newStatusParameter") Status_Type newStatus,@Param("transactionUuidParameter") UUID transactionUuid);

}
