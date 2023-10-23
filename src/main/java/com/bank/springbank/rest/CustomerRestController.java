package com.bank.springbank.rest;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
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
	
	
	@PutMapping("/updateCustomer/{customerId}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable(name = "customerId") Long customerId) {
		
		Customer savedCustomer = customerService.findCustomerById(customerId);
		String customerName = (customer.getName() == null) ? savedCustomer.getName() : customer.getName(); 
		String customerLastName = (customer.getLastName() == null) ? savedCustomer.getLastName() : customer.getLastName(); 		
		Integer customerBankAmount = (customer.getBankAmount() == null) ? savedCustomer.getBankAmount() : customer.getBankAmount();
		Integer customerPassword = (customer.getNewPassword() == null) ? savedCustomer.getPassword() : customer.getNewPassword();
		
		 LocalTime localTimeNow = LocalTime.now();
		 LocalTime timeWithoutNanos = localTimeNow.withNano(0);
		 
		savedCustomer.setCustomerId(savedCustomer.getCustomerId());
		savedCustomer.setName(customerName);
		savedCustomer.setLastName(customerLastName);
		savedCustomer.setPassword(customerPassword);
		savedCustomer.setCreatedDate(savedCustomer.getCreatedDate());
		savedCustomer.setUpdatedDate(timeWithoutNanos);
		savedCustomer.setBankAmount(customerBankAmount);
		
		System.out.println("bbb : " + savedCustomer);
		return customerService.updateCustomer(savedCustomer);			
	}

	
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public Boolean deleteCustomerById(@PathVariable(name = "customerId") Long customerId) {
		
		return customerService.deleteCustomerById(customerId) ? true:false;
		
	}
	
	@PostMapping("/customerLogin")
	private Boolean customerLogin(@RequestBody Map<String, String> request) {
	    Long customerId = Long.parseLong(request.get("customerId"));
	    Integer password = Integer.parseInt(request.get("password"));
	    return customerService.customerLogin(customerId, password);
	}
	
}
