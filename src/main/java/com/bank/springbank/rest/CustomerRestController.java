package com.bank.springbank.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.springbank.model.Customer;
import com.bank.springbank.service.CustomerService;

@RestController
@RequestMapping("/rest/customerAction")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
	    if (customer.getCustomerId() == null) {
	        return ResponseEntity.badRequest().body("customerId field is required");
	    }
	    return ResponseEntity.ok(customerService.saveCustomer(customer));
	}
	
	@GetMapping("/findCustomerById/{customerId}")
	private Customer findCustomerById(
			@PathVariable(name = "customerId") Long customerId) {
		return customerService.findCustomerById(customerId);
	}
	
	@GetMapping("/customerList")
	public List<Customer> getCustomerList() {

		return customerService.getCustomerList();
	}
	
	
	@PutMapping("/update/{customerId}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable(name = "customerId") Long customerId) {
		
		Customer savedCustomer = customerService.findCustomerById(customerId);
		System.out.println("adw : " +savedCustomer );
		String customerName = (customer.getCustomerName() == null) ? savedCustomer.getCustomerName() : customer.getCustomerName(); 
		String customerLastName = (customer.getCustomerLastName() == null) ? savedCustomer.getCustomerLastName() : customer.getCustomerLastName(); 		
		Integer customerBankAmount = (customer.getCustomerBankAmount() == null) ? savedCustomer.getCustomerBankAmount() : customer.getCustomerBankAmount(); 
		
		savedCustomer.setCustomerId(savedCustomer.getCustomerId());
		savedCustomer.setCustomerName(customerName);
		savedCustomer.setCustomerLastName(customerLastName);
		savedCustomer.setCustomerRegisterDate(savedCustomer.getCustomerRegisterDate());
		savedCustomer.setCustomerBankAmount(customerBankAmount);
		
		System.out.println("bbb : " + savedCustomer);
		return customerService.updateCustomer(savedCustomer);			
	}
	
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public Boolean deleteCustomerById(@PathVariable(name = "customerId") Long customerId) {
		
		return customerService.deleteCustomerById(customerId) ? true:false;
		
	}
	
}
