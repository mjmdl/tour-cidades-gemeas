package edu.uniuv.grupo2.tourgemeas.auth;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class SignUpDto {
	@Getter
	public static class Req {
		public static final int NAME_MIN = 3;
		public static final int NAME_MAX = 255;
		public static final int EMAIL_MAX = 255;
		public static final int PASSWORD_MIN = 8;
		public static final int PASSWORD_MAX = 32;

		@NotBlank
		@Length(min = NAME_MIN, max = NAME_MAX)
		private String name;

		@NotBlank
		@Email
		@Length(max = EMAIL_MAX)
		private String email;

		@NotBlank
		@Length(min = PASSWORD_MIN, max = PASSWORD_MAX)
		private String password;
	}

	@Getter
	@AllArgsConstructor
	public static class Res {
		private Long id;
	}
}
