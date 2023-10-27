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
	
	@Query("SELECT c FROM Customer c WHERE c.id = ?1")
	public Customer findCustomerById(Long id);

	
	@Query("DELETE FROM Customer c WHERE c.id = ?1")
	public void deleteCustomerById(Long id);


	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Modifying
	@Query("UPDATE Customer c SET c.name = :nameParameter WHERE c.id = :idParameter")
	public Integer updateCustomer(@Param("nameParameter") String customerName,@Param("idParameter") Long id);


	@Query("SELECT c.password FROM Customer c WHERE c.id = :idParameter")
	public Integer findCustomerPassword(@Param("idParameter") Long id);

}
