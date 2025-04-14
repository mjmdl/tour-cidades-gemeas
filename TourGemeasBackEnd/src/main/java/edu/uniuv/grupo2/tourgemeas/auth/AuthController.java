package edu.uniuv.grupo2.tourgemeas.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {
	public static final String ACCESS_TOKEN_COOKIE = "access-token";
	public static final String SIGN_UP_PATH = "/auth/sign-up";
	public static final String SIGN_IN_PATH = "/auth/sign-in";
	public static final String CHECK_SESSION_PATH = "/auth";

	@Value("${security.jwt.duration-seconds}")
	private final Long JWT_DURATION_SECONDS = null;

	private final AuthService authService;

	@PostMapping(path = SIGN_UP_PATH)
	public ResponseEntity<SignUpResult> signUp(@RequestBody @Valid SignUp signUpDto) {
		return ResponseEntity.ok(authService.signUp(signUpDto));
	}

	@PostMapping(path = SIGN_IN_PATH)
	public ResponseEntity<String> signIn(@RequestBody @Valid SignIn signInDto) {
		String token = authService.signIn(signInDto);
		ResponseCookie cookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE, token)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(JWT_DURATION_SECONDS)
			.sameSite("Strict")
			.build();
		return ResponseEntity
			.ok()
			.header(HttpHeaders.SET_COOKIE, cookie.toString())
			.body(token);
	}

	@PostMapping(path = CHECK_SESSION_PATH)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserDetails> checkSession(Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		return ResponseEntity.ok(userDetails);
	}
}
