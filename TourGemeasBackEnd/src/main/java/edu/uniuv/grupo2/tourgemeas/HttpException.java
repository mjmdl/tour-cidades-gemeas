package edu.uniuv.grupo2.tourgemeas;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HttpException extends RuntimeException {
	private HttpStatus status;
	private String name;
	private String message;
}
