package com.capgemini.user.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFoundExpetion(ResourceNotFound ex){
		Map< String, Object> map = new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("status", false);
		map.put("Http status", HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
}
