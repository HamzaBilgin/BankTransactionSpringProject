package com.bank.springbank.responseHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHandler {

	public ResponseEntity<Object> resultMessageResponse(Integer statusCode, Object... args) {
	    Map<String, Object> response = new HashMap<>();
	    for (int i = 0; i < args.length-1; i += 2) {
	        if (i + 1 < args.length && args[i] instanceof String) {
	            response.put((String) args[i], args[i + 1]);
	        }
	    }
	    return ResponseEntity.status(statusCode).body(response);
	}
}
