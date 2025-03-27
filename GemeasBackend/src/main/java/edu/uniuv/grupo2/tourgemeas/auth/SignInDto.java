package edu.uniuv.grupo2.tourgemeas.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class SignInDto {
	@Getter
	@AllArgsConstructor
	public static class Req {
		@NotBlank
		private String username;
		
		@NotBlank
		private String password;
	}

	@Getter
	@AllArgsConstructor
	public static class Res {
		private String accessToken;
	}
}
