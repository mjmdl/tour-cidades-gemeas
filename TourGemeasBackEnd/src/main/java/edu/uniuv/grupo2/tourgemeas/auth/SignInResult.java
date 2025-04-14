package edu.uniuv.grupo2.tourgemeas.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInResult {
	private String token;
	private Boolean admin;
}
