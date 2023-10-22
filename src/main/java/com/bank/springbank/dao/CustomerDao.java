package com.bank.springbank.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.springbank.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>{
	

	@Query("SELECT c FROM Customer c WHERE c.customerId = ?1")
	public Customer findCustomerById(Long customerId);

	
	@Query("DELETE FROM Customer c WHERE c.customerId = ?1")
	public void deleteCustomerById(Long customerId);


	




}
