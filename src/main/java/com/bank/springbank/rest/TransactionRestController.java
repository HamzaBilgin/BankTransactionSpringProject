package com.bank.springbank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.springbank.model.Customer;
import com.bank.springbank.model.Transactions;
import com.bank.springbank.responseHandler.ResponseHandler;
import com.bank.springbank.service.TransactionsService;


@RestController
@RequestMapping("/rest/transaction")
public class TransactionRestController {

	@Autowired
	private TransactionsService transactionsService;
	@Autowired
	private ResponseHandler responseHandler;
	
	
	@PostMapping("/save")
	public Transactions saveTransaction(@RequestBody Transactions transaction) {	   
	   return transactionsService.saveTransaction(transaction);
	}
	
	@GetMapping("/getTransactionsForsourceAccountId/{id}")
	public ResponseEntity<Object> getTransactionsForsourceAccountId(@PathVariable(name = "id") Long id) {
		System.out.println("adwadwad : " + transactionsService.getTransactionsForsourceAccountId(id));
		return responseHandler.resultMessageResponse( 200,new Object[]{"TransactionsList", transactionsService.getTransactionsForsourceAccountId(id)});
	}	
}
