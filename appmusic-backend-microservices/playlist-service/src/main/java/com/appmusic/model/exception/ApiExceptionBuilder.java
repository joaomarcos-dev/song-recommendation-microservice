package com.appmusic.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//@Hardcoded should be written in a properties file
public class ApiExceptionBuilder {

	@ExceptionHandler(IncorrectSyntaxException.class)
	public ResponseEntity<Object> badRequest(IncorrectSyntaxException aet) {
		
		ApiError error = ApiError.builder().withStatusCode(HttpStatus.BAD_REQUEST)
				
				.withErrorTitle("Incorrect query params")
				
				.withMessage("We could not process the sent query params. Verify those and try again.").withDetail(null)
				
				.build();

		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<Object> notFound(CityNotFoundException aet) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ApiError error = ApiError.builder().withStatusCode(status).withErrorTitle("City not found")
				
				.withMessage("We could not find the city you looking for. Try another name.").withDetail(null).build();
		
		return new ResponseEntity<Object>(error, status);
	}

	@ExceptionHandler(InternalErrorException.class)
	public ResponseEntity<Object> internalError(InternalErrorException aet) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ApiError error = ApiError.builder().withStatusCode(status)
				
				.withErrorTitle("Problem with the server")
				
				.withMessage("We could not process the request due to interal errors. We'll be back in a while")
				
				.withDetail(null).build();
		
		return new ResponseEntity<Object>(error, status);
	}

}
