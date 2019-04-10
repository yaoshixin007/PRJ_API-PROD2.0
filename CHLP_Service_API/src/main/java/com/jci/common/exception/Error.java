package com.jci.common.exception;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/**
 * class Error
 * 
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class Error {
    
	 private HttpStatus status;
	    private Integer statusCode;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	    private Timestamp timestamp;
	    private String message;
	    private String debugMessage;
	    private List<ApiSubError> subErrors;
	   

	    /**
		 * @return the status
		 */
		public HttpStatus getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(HttpStatus status) {
			this.status = status;
		}

		/**
		 * @return the timestamp
		 */
		public Timestamp getTimestamp() {
			return timestamp;
		}

		/**
		 * @param timestamp the timestamp to set
		 */
		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}

		/**
		 * @return the debugMessage
		 */
		public String getDebugMessage() {
			return debugMessage;
		}

		/**
		 * @param debugMessage the debugMessage to set
		 */
		public void setDebugMessage(String debugMessage) {
			this.debugMessage = debugMessage;
		}

		/**
		 * @return the subErrors
		 */
		public List<ApiSubError> getSubErrors() {
			return subErrors;
		}

		/**
		 * @param subErrors the subErrors to set
		 */
		public void setSubErrors(List<ApiSubError> subErrors) {
			this.subErrors = subErrors;
		}

		private Error() {
	     timestamp = new Timestamp(System.currentTimeMillis());
	    }

	    Error(HttpStatus status) {
	        this();
	        this.status = status;
	    }

	    Error(HttpStatus status, Throwable ex) {
	        this();
	        this.status = status;
	        this.message = "Unexpected error";
	        this.debugMessage = ex.getLocalizedMessage();
	    }

	    Error(HttpStatus status, String message, Throwable ex) {
	        this();
	        this.status = status;
	        this.message = message;
	        this.debugMessage = ex.getLocalizedMessage();
	    }

	    /**
		 * @param status
		 * @param statusCode
		 * @param message
		 * @param ex
		 */
		public Error(HttpStatus status, int statusCode, String message, Throwable ex) {
			  this();
		        this.status = status;
		        this.statusCode = statusCode;
		        this.message = message;
		        this.debugMessage = ex.getLocalizedMessage();	}
		
		public Error(HttpStatus status, int statusCode,  Throwable ex) {
			  this();
		        this.status = status;
		        this.statusCode = statusCode;
		        this.debugMessage = ex.getLocalizedMessage();	}


		private void addSubError(ApiSubError subError) {
	        if (subErrors == null) {
	            subErrors = new ArrayList<>();
	        }
	        subErrors.add(subError);
	    }

	    private void addValidationError(String object, String field, Object rejectedValue, String message) {
	        addSubError(new ApiValidationError(object, field, rejectedValue, message));
	    }

	    private void addValidationError(String object, String message) {
	        addSubError(new ApiValidationError(object, message));
	    }

	    private void addValidationError(FieldError fieldError) {
	        this.addValidationError(
	                fieldError.getObjectName(),
	                fieldError.getField(),
	                fieldError.getRejectedValue(),
	                fieldError.getDefaultMessage());
	    }

	    void addValidationErrors(List<FieldError> fieldErrors) {
	        fieldErrors.forEach(this::addValidationError);
	    }

	    private void addValidationError(ObjectError objectError) {
	        this.addValidationError(
	                objectError.getObjectName(),
	                objectError.getDefaultMessage());
	    }

	    void addValidationError(List<ObjectError> globalErrors) {
	        globalErrors.forEach(this::addValidationError);
	    }

	    private void addValidationError(ConstraintViolation<?> cv) {
	        this.addValidationError(
	                cv.getRootBeanClass().getSimpleName(),
	                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
	                cv.getInvalidValue(),
	                cv.getMessage());
	    }

	    void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
	        constraintViolations.forEach(this::addValidationError);
	    }



	    public Integer getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(Integer statusCode) {
			this.statusCode = statusCode;
		}



		interface  ApiSubError {}

	    class ApiValidationError implements ApiSubError {
	        private String object;
	        private String field;
	        private Object rejectedValue;
	        private String message;

	        ApiValidationError(String object, String field,Object rejectedValue,String message) {
	        	this.object=object;
	            this.field =field;
	            this.rejectedValue=rejectedValue;
	            this.message=message;
	    
	        }

	        ApiValidationError(String object, String message) {
	            this.object = object;
	            this.message = message;
	        }

	    }
	}

	class LowerCaseClassNameResolver extends TypeIdResolverBase {

	    @Override
	    public String idFromValue(Object value) {
	        return value.getClass().getSimpleName().toLowerCase();
	    }

	    @Override
	    public String idFromValueAndType(Object value, Class<?> suggestedType) {
	        return idFromValue(value);
	    }

	    @Override
	    public JsonTypeInfo.Id getMechanism() {
	        return JsonTypeInfo.Id.CUSTOM;
	    }
	}