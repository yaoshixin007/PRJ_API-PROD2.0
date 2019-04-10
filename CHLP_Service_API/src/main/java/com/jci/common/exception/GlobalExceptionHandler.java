
package com.jci.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * class GlobalExceptionHandler
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	static final String ERROR = "error";
	static final String ERROR_MESSAGE = "  error occurred";
	static final String STATUS = "Status";
	static final String STATUS_CODE = "StatusCode";
	static final String MESSAGE = "Message";

	/**
	 * Instantiates a new global exception handler.
	 */
	public GlobalExceptionHandler() {
		super();

	}
	
	
	@ExceptionHandler({ HttpClientErrorException.class })
	protected ResponseEntity<Object> handleClientError(final RuntimeException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("Exception in Authorization Framework: {}", ex);
		HttpClientErrorException exp = (HttpClientErrorException) ex;
		final Error apiError = new Error(exp.getStatusCode(), exp.getStatusCode().value(), exp.getMessage(), ex);
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex);
        logger.error("Generic Exception in JCI API application: {}", ex);
		final Error apiError = new Error(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex);
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(Error apiError) {
		HttpHeaders header = new HttpHeaders();
		header.set(STATUS, apiError.getStatus().name());
		header.set(STATUS_CODE, apiError.getStatusCode().toString());
		header.set(MESSAGE, apiError.getDebugMessage());
		return new ResponseEntity<>(apiError, header, apiError.getStatus());
	}

	@ExceptionHandler({ BatteryOperationBusinessException.class })
    protected ResponseEntity<Object> handleSCRDConflict(final BatteryOperationBusinessException ex, final WebRequest request) {

           final Error apiError = new Error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex);
           return buildResponseEntity(apiError);

    }
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String error = "Parameter Error : Missing Parameters: " + ex.getParameterName();
	    final Error apiError =  new Error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), error, ex);
	    apiError.setDebugMessage(error);
	    return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ StringTooLongException.class })
	protected ResponseEntity<Object> handleStringTooLong(final StringTooLongException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("String length Check exception.");
		String error = "Parameter Error : Exceeds String Length: " + ex.getMessage();
		final Error apiError = new Error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), error, ex);
		apiError.setDebugMessage(error);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ NumberFormatException.class })
	protected ResponseEntity<Object> handleNumberFormat(final NumberFormatException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("Number Format Exception.");
		String error = "Parameter Error : Data Value Should Be Numeric: " + ex.getMessage();
		final Error apiError = new Error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), error, ex);
		apiError.setDebugMessage(error);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ BusinessValidationException.class })
	protected ResponseEntity<Object> handleValidationError(final BusinessValidationException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("Business Validation exception.");
		final Error apiError = new Error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex);
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ IllegalArgumentException.class })
	protected ResponseEntity<Object> handleAlphanumericString(final IllegalArgumentException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("Illegal Argument Exception.");
		String error = "Parameter Error : Data Value Should Be Alphanumeric Or Numeric: " + ex.getMessage();
		final Error apiError = new Error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), error, ex);
		apiError.setDebugMessage(error);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ InvalidDateFormatException.class })
	protected ResponseEntity<Object> handleDateFormat(final InvalidDateFormatException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("Illegal Argument Exception.");
		String error = "Parameter Error : Date Value Should Be Valid and In 'yyyyMMdd' format: " + ex.getMessage();
		final Error apiError = new Error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), error, ex);
		apiError.setDebugMessage(error);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ SignatureException.class })
	protected ResponseEntity<Object> handleClientSignatureError(final RuntimeException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("Exception in Authorization Framework: {}", ex);
		SignatureException exp = (SignatureException) ex;
		final Error apiError = new Error(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), exp.getMessage(), ex);
		apiError.setDebugMessage(ex.getMessage());
		apiError.setDebugMessage("Invalid Sigature. ");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler({ ExpiredJwtException.class })
	protected ResponseEntity<Object> handleExpiredToken(final RuntimeException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error(ERROR, ex.getCause());
		logger.error("Exception in Authorization Framework: {}", ex);
		ExpiredJwtException exp = (ExpiredJwtException) ex;
		final Error apiError = new Error(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), exp.getMessage(), ex);
		apiError.setDebugMessage("JWT token has expired. ");
		return buildResponseEntity(apiError);
	}
}
