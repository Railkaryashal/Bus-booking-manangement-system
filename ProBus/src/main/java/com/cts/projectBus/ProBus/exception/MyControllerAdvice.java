package com.cts.projectBus.ProBus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.projectBus.ProBus.model.ApiResponseError;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
	 @ExceptionHandler(value = BusNotFoundException.class)
		public ResponseEntity<ApiResponseError>  handleProductNotFoundException(BusNotFoundException ex, WebRequest request) {
		 ApiResponseError obj = new ApiResponseError();
			obj.setMessage(ex.getMessage());
			obj.setStatusCode("404");
			return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
			
		}

	    @ExceptionHandler(value = TransactionException.class)
		public ResponseEntity<ApiResponseError>  handleTransactionException(TransactionException ex, WebRequest request) {
			ApiResponseError obj = new ApiResponseError();
			obj.setMessage(ex.getMessage());
			obj.setStatusCode("404");
			return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
			
		}
	    
	    @ExceptionHandler(value = UserNotFoundException.class)
		public ResponseEntity<ApiResponseError>  handleTransactionException(UserNotFoundException ex, WebRequest request) {
			ApiResponseError obj = new ApiResponseError();
			obj.setMessage(ex.getMessage());
			obj.setStatusCode("404");
			return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
			
		}
		 @ExceptionHandler(value = CannotDeleteBusException.class)
			public ResponseEntity<ApiResponseError>  handleDeleteBusFoundException1(CannotDeleteBusException ex, WebRequest request) {
			 ApiResponseError obj = new ApiResponseError();
				obj.setMessage(ex.getMessage());
				obj.setStatusCode("500");
				return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
			}
}
