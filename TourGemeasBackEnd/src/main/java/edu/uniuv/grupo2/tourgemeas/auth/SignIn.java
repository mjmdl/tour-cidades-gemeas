package edu.uniuv.grupo2.tourgemeas.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SignIn {
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
}
