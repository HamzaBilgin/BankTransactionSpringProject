package com.bank.springbank.dao;



import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
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


	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Modifying
	@Query("UPDATE Customer c SET c.name = :nameParameter WHERE c.customerId = :customerIdParameter")
	public Integer updateCustomer(@Param("nameParameter") String customerName,@Param("customerIdParameter") Long customerId);


	@Query("SELECT c.password FROM Customer c WHERE c.customerId = :customerIdParameter")
	public Integer findCustomerPassword(@Param("customerIdParameter") Long customerId);

}
