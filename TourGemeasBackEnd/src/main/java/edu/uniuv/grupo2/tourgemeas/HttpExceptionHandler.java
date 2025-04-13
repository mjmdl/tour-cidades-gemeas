package edu.uniuv.grupo2.tourgemeas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {
	@ExceptionHandler(HttpException.class)
	public ResponseEntity<Map<String, String>> handleConflict(HttpException exception) {
		Map<String, String> result = new HashMap<>();
		result.put("name", exception.getName());
		result.put("message", exception.getMessage());
		return ResponseEntity
			.status(exception.getStatus())
			.body(result);
	}
}
