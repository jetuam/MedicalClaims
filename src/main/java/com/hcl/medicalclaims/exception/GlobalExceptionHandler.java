package com.hcl.medicalclaims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author srinivas 
 * Used to throw global exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @param Exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ResponseError> commonException(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(FileUploadfailedException.class)
	public ResponseEntity<ResponseError> fileUploadfailedException(FileUploadfailedException exception) {
		ResponseError errorResponse = new ResponseError();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(HospitalNotFoundException.class)
	public ResponseEntity<ResponseError> hospitalNotFoundException(HospitalNotFoundException exception) {
		ResponseError errorResponse = new ResponseError();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<ResponseError> policyNotFoundException(PolicyNotFoundException exception) {
		ResponseError errorResponse = new ResponseError();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
