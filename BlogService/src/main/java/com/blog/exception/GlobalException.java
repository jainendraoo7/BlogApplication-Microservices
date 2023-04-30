package com.blog.exception;

import java.time.LocalDateTime;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	
	  @ExceptionHandler(BlogException.class)
	  public ResponseEntity<ErrorDetails> AdminException(BlogException exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails, null, HttpStatus.SC_BAD_REQUEST);
	    }
	
	  
	  @ExceptionHandler(UserException.class)
	  public ResponseEntity<ErrorDetails> AdminException(UserException exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails, null, HttpStatus.SC_BAD_REQUEST);
	        
	    }
	  
}
