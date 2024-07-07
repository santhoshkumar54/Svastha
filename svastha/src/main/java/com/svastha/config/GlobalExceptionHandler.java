package com.svastha.config;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.svastha.logs.LogServiceFactory;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	// Handle 400 Bad Request
	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleBadRequest(Exception ex) {
		LogServiceFactory.getService().logError("Exception caught 400: ", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request: " + ex.getMessage());
	}

	// Handle 401 Unauthorized
	@ExceptionHandler({ Unauthorized.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> handleUnauthorized(Exception ex) {
		LogServiceFactory.getService().logError("Exception caught 401: ", ex);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + ex.getMessage());
	}

	// Handle 403 Forbidden
	@ExceptionHandler({ Forbidden.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<Object> handleForbidden(Exception ex) {
		LogServiceFactory.getService().logError("Exception caught 403: ", ex);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden: " + ex.getMessage());
	}

	// Handle 404 Not Found
	@ExceptionHandler({ ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNotFound(Exception ex) {
		LogServiceFactory.getService().logError("Exception caught 404: ", ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + ex.getMessage());
	}

	// Handle 500 Internal Server Error
	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleServerError(Exception ex) {
		LogServiceFactory.getService().logError("Exception caught 500: ", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Internal server error: " + ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		LogServiceFactory.getService().logError("Exception caught: ", ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
