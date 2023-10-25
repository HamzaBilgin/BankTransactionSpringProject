package com.bank.springbank.rest;

import java.time.LocalTime;
import java.util.HashMap;
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
import com.bank.springbank.responseHandler.ResponseHandler;
import com.bank.springbank.service.CustomerService;

@RestController
@RequestMapping("/rest/customerAction")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ResponseHandler responseHandler;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
	    
//	    return ResponseEntity.ok(customerService.saveCustomer(customer));
	    
	   
	    
	    try {
	    	if (customer.getCustomerId() != null) {
	    		 Customer savedCustomer = customerService.saveCustomer(customer);
	 	        return responseHandler.resultMessageResponse(200, "customer", savedCustomer, "message", "Müşteri kaydedildi.");
		        
		    }else {
		    	 return responseHandler.resultMessageResponse(400, "message", "customerId field is required");
		    }
	        // Müşteriyi kaydetmeye çalış
	       
	    } catch (Exception e) {
	        // Hata durumunda bir hata mesajı döndür
	    	return responseHandler.resultMessageResponse( 400,new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
	    }
	}
	
	@GetMapping("/findCustomerById/{customerId}")
	private ResponseEntity<Object> findCustomerById(
			@PathVariable(name = "customerId") Long customerId) {
		try {
			if(customerService.findCustomerById(customerId) != null) {
				return responseHandler.resultMessageResponse( 200,new Object[]{"Customer", customerService.findCustomerById(customerId)});
			}
			else {
				return responseHandler.resultMessageResponse( 200,new Object[]{"result", "Başarılı", "message", "Müşteri Bulunamadı!"});
			}
		} catch (Exception e) {
			return responseHandler.resultMessageResponse( 400,new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
		
	}
	
	@GetMapping("/customerList")
	public ResponseEntity<Object> getCustomerList() {
		try {
			if(customerService.getCustomerList().size() != 0 ) {
				return responseHandler.resultMessageResponse( 200,new Object[]{"Customers", customerService.getCustomerList()});
			}else {
				return responseHandler.resultMessageResponse( 200,new Object[]{"result", "Başarılı", "message", "Hiç müşteri kayıtlı değil"});
			}
		} catch (Exception e) {
			return responseHandler.resultMessageResponse( 400,new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
	}
	
	
	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable(name = "customerId") Long customerId) {
		
		
		try {
			
			if(customerService.findCustomerById(customerId) != null) {
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
				return responseHandler.resultMessageResponse( 200,new Object[]{"result","Basarılı","Customers", customerService.updateCustomer(savedCustomer)});
			}else {
				return responseHandler.resultMessageResponse( 200,new Object[]{"result","Başarılı","message", "Hiç müşteri kayıtlı değil"});
			}
			
	
			
		} catch (Exception e) {
			return responseHandler.resultMessageResponse( 400,new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
	}

	
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<Object> deleteCustomerById(@PathVariable(name = "customerId") Long customerId) {
		
		try {
			if(customerService.deleteCustomerById(customerId) == true) {
				return responseHandler.resultMessageResponse( 200,new Object[]{"result", true,"message", "Silme işlemi başarılı"});
			}else {
				return responseHandler.resultMessageResponse( 200,new Object[]{"result", false,"message", "Silme işlemi başarısız"});
			}
		} catch (Exception e) {
			return responseHandler.resultMessageResponse( 400,new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
		
		
		
	}
	
	@PostMapping("/customerLogin")
    public ResponseEntity<Object> customerLogin(@RequestBody Map<String, String> request) {
        Long customerId;
        Integer password;
        
        try {
            customerId = Long.parseLong(request.get("customerId"));
            password = Integer.parseInt(request.get("password"));
            boolean loginResult = customerService.customerLogin(customerId, password);
            
            if (loginResult) {
                return responseHandler.resultMessageResponse(200,new Object[]{"result", true, "message", "Başarılı"});
            } else {
                return responseHandler.resultMessageResponse(401,new Object[]{"result", false, "message", "Başarısız"});
            }
        } catch (NumberFormatException e) {
        	return responseHandler.resultMessageResponse( 400,new Object[]{"result", true, "message", "Hatalı giriş bilgileri"});
        }catch (Exception e) {
			return responseHandler.resultMessageResponse( 400,new Object[]{"result", "Başarısız", "message", "Beklenmeye Hata"});
		}
        
        
    }
	
}
