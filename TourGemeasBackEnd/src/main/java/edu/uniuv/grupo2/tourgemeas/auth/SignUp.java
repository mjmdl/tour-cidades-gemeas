package edu.uniuv.grupo2.tourgemeas.auth;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SignUp {
	@NotEmpty
	@Length(min = 5, max = 255)
	private String name;
	
	@NotEmpty
	@Email
	@Length(min = 5, max = 255)
	private String email;
	
	@NotEmpty
	@Length(min = 8, max = 32)
	private String password;

	private Boolean admin = false;
}
