package com.grocery.assignment.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grocery.assignment.common.ApiResponse;

@RestControllerAdvice
public class CommonExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public  ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
	{
		String msg=ex.getMessage();
		ApiResponse apiResponse= new ApiResponse(msg,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> respMap=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String msg = error.getDefaultMessage();
			respMap.put(fieldName, msg);
		});
		return new ResponseEntity<Map<String,String>>(respMap,HttpStatus.BAD_REQUEST);
	}
}
