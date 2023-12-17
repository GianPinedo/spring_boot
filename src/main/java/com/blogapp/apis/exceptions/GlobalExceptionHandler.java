package com.blogapp.apis.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapp.apis.payload.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFindException.class)
	public ResponseEntity<APIResponse> ResouceNotFoundExceptionHandler(ResourceNotFindException resourceNotFindException){
		APIResponse apiResponse = new APIResponse();
		apiResponse.setIsSucess(false);
		apiResponse.setResponseCode(HttpStatus.NOT_FOUND);
		apiResponse.setMessage(resourceNotFindException.getMessage());
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler
	(MethodArgumentNotValidException ex){
		Map<String, String> errorMap = new HashMap<>();
		
		ex.getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorMap.put(fieldName, errorMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	
}
