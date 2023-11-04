package com.bank.springbank.rest;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.springbank.genericMetods.GenericMetods;
import com.bank.springbank.model.Customer;
import com.bank.springbank.model.Status_Type;
import com.bank.springbank.model.Transaction_Type;
import com.bank.springbank.model.Transactions;
import com.bank.springbank.responseHandler.ResponseHandler;
import com.bank.springbank.service.TransactionsService;


@RestController
@RequestMapping("/rest/transaction")
public class TransactionRestController{

	@Autowired
	private TransactionsService transactionsService;
	@Autowired
	private ResponseHandler responseHandler;
	@Autowired
	private GenericMetods genericMetods;
	@PostMapping("/save")
	public ResponseEntity<Object> saveTransaction(@RequestBody Map<String, String> request) {	
		 try {
			 Transactions transaction = new Transactions();
			  if (!genericMetods.isValidEnum(request.get("status"), Status_Type.class)||!genericMetods.isValidEnum(request.get("transactionType"), Transaction_Type.class)) {
	               
	            	 return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Parametre Verisi Uygun Değil"});
	            }
	            try {
	            	transaction.setTransactionType( Transaction_Type.valueOf(request.get("transactionType")));
		            transaction.setSourceAccountId(Long.parseLong(request.get("sourceAccountId")));
		            transaction.setTargetAccountId(Long.parseLong(request.get("targetAccountId")));
		            transaction.setAmount(Double.parseDouble(request.get("amount")));
		            transaction.setStatus(Status_Type.valueOf(request.get("status")));
		           
				} catch (Exception e) {
					 return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Parametre Tipi Hatalı"});
				}
	            transactionsService.saveTransaction(transaction);
	            return responseHandler.resultMessageResponse(200, "transaction", transactionsService.saveTransaction(transaction), "message", "İşlem başarılı şekilde kayıt edildi!");
	        } catch (Exception e) {
	            return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
	        }
	  
	}
	
	@PostMapping("/getTransactionsBysourceAccountId")
	public ResponseEntity<Object> getTransactionsForsourceAccountId(@RequestBody Map<String, String> request) {
		try {
			Long sourceAccountId = Long.parseLong(request.get("sourceAccountId"));
			return responseHandler.resultMessageResponse( 200,new Object[]{"TransactionsList", transactionsService.getTransactionsBysourceAccountId(sourceAccountId)});
		} catch (Exception e) {
			return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
	}
	
	@PostMapping("/getTransactionsBytargetAccountId")
	public ResponseEntity<Object> getTransactionsBytargetAccountId(@RequestBody Map<String, String> request) {
		try {
			Long targetAccountId = Long.parseLong(request.get("targetAccountId"));
			return responseHandler.resultMessageResponse( 200,new Object[]{"TransactionsList", transactionsService.getTransactionsBytargetAccountId(targetAccountId)});
		} catch (Exception e) {
			return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
	}
	
	@PostMapping("/getTransactionsByType")
	public ResponseEntity<Object> getTransactionsByType(@RequestBody Map<String, String> request) {
		try {
			if(!genericMetods.isValidEnum(request.get("transactionType"), Transaction_Type.class)) {
				return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Parametre Verisi Uygun Değil"});
			}
			 String transactionTypeValue = request.get("transactionType");
			    Transaction_Type transactionType = Transaction_Type.valueOf(transactionTypeValue);
		    return responseHandler.resultMessageResponse(200, new Object[]{"TransactionsList", transactionsService.getTransactionsByType(transactionType)});

		} catch (Exception e) {
			return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}		  
	}
	
	@PostMapping("/updateTransactionStatus")
	public ResponseEntity<Object> updateTransactionStatus(@RequestBody Map<String, String> request) {
		try {
			if(!genericMetods.isValidEnum(request.get("status"), Status_Type.class)) {
				return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Parametre Verisi Uygun Değil"});
			}
			String uuidString = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";
			UUID uuid = UUID.fromString(request.get("transactionUuid"));
		    String status = request.get("status");
		    Status_Type statusType = Status_Type.valueOf(status);
		    return responseHandler.resultMessageResponse(200, new Object[]{"TransactionsList", transactionsService.updateTransactionStatus(statusType,uuid)});
		} catch (Exception e) {
			return responseHandler.resultMessageResponse(400, new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
	}
	
	

}
