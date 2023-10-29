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
	    try {
	    	if (customer.getId() != null) {
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
	@PostMapping("/findCustomerById")
	private ResponseEntity<Object> findCustomerById(@RequestBody Map<String, String> request) {
		
		try {
			Long id = Long.parseLong(request.get("id"));
			if(customerService.findCustomerById(id) != null) {
				return responseHandler.resultMessageResponse( 200,new Object[]{"Customer", customerService.findCustomerById(id)});
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
	@PostMapping("/updateCustomer")
	public ResponseEntity<Object> updateCustomer(@RequestBody Map<String, String> request) {	
		try {
			Long id = Long.parseLong(request.get("id"));
			String name  = request.get("name");
			String lastName  = request.get("lastName");
			String newPassword  =  request.get("newPassword");
			String bankAmount  = request.get("customerBankAmount");
			if(customerService.findCustomerById(id) != null) {
				Customer savedCustomer = customerService.findCustomerById(id);
				String customerName = (name == null) ? savedCustomer.getName() : name; 
				String customerLastName = (lastName == null) ? savedCustomer.getLastName() : lastName; 		
				Integer customerBankAmount = (bankAmount == null) ? savedCustomer.getBankAmount() : Integer.parseInt(bankAmount);
				String customerPassword = (newPassword == null) ? savedCustomer.getPassword() : newPassword;
				
				LocalTime localTimeNow = LocalTime.now();
				LocalTime timeWithoutNanos = localTimeNow.withNano(0);
				 
				savedCustomer.setId(savedCustomer.getId());
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

	
	@PostMapping("/deleteCustomerById")
	public ResponseEntity<Object> deleteCustomerById(@RequestBody Map<String, String> request) {
		
		try {
			Long id = Long.parseLong(request.get("id"));
			if(customerService.deleteCustomerById(id) == true) {
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
