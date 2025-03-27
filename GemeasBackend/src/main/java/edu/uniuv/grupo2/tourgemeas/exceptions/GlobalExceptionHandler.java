package edu.uniuv.grupo2.tourgemeas.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException methodArgumentNotValid)
	{
		Map<String, String> errors = new HashMap<>();
		methodArgumentNotValid.getBindingResult().getFieldErrors()
			.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleInvalidArgument(IllegalArgumentException illegalArgument)
	{
		return illegalArgument.getMessage();
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String handleAccessDenied(AccessDeniedException accessDenied)
	{
		return accessDenied.getMessage();
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequest(BadRequestException badRequest)
	{
		return badRequest.getMessage();
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFound(NotFoundException notFound)
	{
		return notFound.getMessage();
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleUnauthorized(UnauthorizedException unauthorized)
	{
		return unauthorized.getMessage();
	}
}
