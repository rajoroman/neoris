package com.customerAccount.services.exceptions;

import org.hibernate.TransactionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

	private static final String DATA_EXIST = "Data already exists";
	
	private static final String CUSTOMER_EXIST = "Customers already exists";

	private static final String CONNECTION_CLOSE = "Error establishing a database connection";
	
	private static final String CUSTOMER_NOT_FOUND = "Customer not found";
	
	private static final String CUSTOMER_OR_TYPEACCOUNT_NOT_FOUND = "Customer or Type Account not found";
	
	public static final String INSUFFICIENT_BALANCE = "Insufficent balance";

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorInfo> runtimeException(HttpServletRequest request, RuntimeException e) {

		ErrorInfo errorInfo = null;
		HttpStatus httpStatus = null;

		if (e.getCause() instanceof DataIntegrityViolationException) {
			errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), DATA_EXIST, request.getRequestURI());
			httpStatus = HttpStatus.BAD_REQUEST;

		} else if (e.getCause() instanceof TransactionException) {
			errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), CONNECTION_CLOSE,
					request.getRequestURI());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			
		} else if (e.getCause() instanceof ExceptionCustomService) {
			
			ExceptionCustomService cause = (ExceptionCustomService) e.getCause();
			
			if(cause.getCode() == ExceptionCustomService.INSUFFICIENT_BALANCE) {
				errorInfo = new ErrorInfo(HttpStatus.OK.value(), INSUFFICIENT_BALANCE,
						request.getRequestURI());
				httpStatus = HttpStatus.OK;
				
			}else if(cause.getCode() == ExceptionCustomService.CUSTOMER_NOT_FOUND) {
				
				errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), CUSTOMER_OR_TYPEACCOUNT_NOT_FOUND,
						request.getRequestURI());
				httpStatus = HttpStatus.NOT_FOUND;
			}
			
		}
		return new ResponseEntity<>(errorInfo, httpStatus);
	}

	@ExceptionHandler(ExceptionCustomService.class)
	public ResponseEntity<ErrorInfo> notDataFoundException(HttpServletRequest request, ExceptionCustomService e) {

		ErrorInfo errorInfo = null;
		HttpStatus httpStatus = null;
		
		if(e.getCode() == ExceptionCustomService.CUSTOMER_NOT_FOUND) {
			errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), CUSTOMER_NOT_FOUND, request.getRequestURI());
			httpStatus = HttpStatus.NOT_FOUND;
		}
		
		if(e.getCode() == ExceptionCustomService.CUSTOMER_ALREADY_EXISTIS) {
			errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), CUSTOMER_EXIST, request.getRequestURI());
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		
		if(e.getCode() == ExceptionCustomService.INSUFFICIENT_BALANCE) {
			errorInfo = new ErrorInfo(HttpStatus.OK.value(), INSUFFICIENT_BALANCE, request.getRequestURI());
			httpStatus = HttpStatus.OK;
		}

		return new ResponseEntity<>(errorInfo, httpStatus);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> notDataFoundException(HttpServletRequest request, Exception e) {

		ErrorInfo errorInfo = null;
		HttpStatus httpStatus = null;

		return new ResponseEntity<>(errorInfo, httpStatus);
	}
}
