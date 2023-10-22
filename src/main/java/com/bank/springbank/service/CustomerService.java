package com.bank.springbank.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.springbank.dao.CustomerDao;
import com.bank.springbank.model.Customer;

import lombok.Getter;
import lombok.Setter;



@Service
public class CustomerService {

	@Autowired
	@Getter
	@Setter
	private CustomerDao customerDao;
	
	public Customer saveCustomer(Customer customer) {
		 LocalTime localTimeNow = LocalTime.now();
		 LocalTime timeWithoutNanos = localTimeNow.withNano(0);
		 customer.setCustomerRegisterDate(localTimeNow);
		 customer.setCustomerBankAmount(0);
		return customerDao.save(customer);
	}
	
	public Customer findCustomerById(Long customerId) {
		return customerDao.findCustomerById(customerId);
	}
	
	public List<Customer> getCustomerList() {
		return customerDao.findAll();
	}
	
	public Customer updateCustomer(Customer customer) {
		
		return customerDao.save(customer);
	}
	
	public Boolean deleteCustomerById(Long customerId) {
		customerDao.deleteById(customerId);;
		return true;
	}
	
}
