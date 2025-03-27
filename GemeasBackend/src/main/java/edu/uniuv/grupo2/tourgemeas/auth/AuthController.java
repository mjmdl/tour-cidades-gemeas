package edu.uniuv.grupo2.tourgemeas.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AuthController {
	public static final String SIGN_UP_PATH = "/auth/sign-up";
	public static final String SIGN_IN_PATH = "/auth/sign-in";
	public static final String SIGN_OUT_PATH = "/auth/sign-out";
	public static final String ACCESS_TOKEN_COOKIE = "access-token"; // TODO(mjmdl): Mover isso para outro lugar.
	public static final int ACCESS_TOKEN_DURATION_SECONDS = 60 * 60 * 24 * 7; // TODO(mjmdl): Mover isso para outro lugar.

	private final AuthService authService;

	@PostMapping(path = SIGN_UP_PATH)
	public ResponseEntity<SignUpDto.Res> signUp(@RequestBody @Valid SignUpDto.Req requestBody)
	{
		final var responseData = authService.signUp(requestBody);
		return ResponseEntity.ok(responseData); 
	}

	@PostMapping(path = SIGN_IN_PATH)
	public ResponseEntity<SignInDto.Res> signIn(@RequestBody @Valid SignInDto.Req requestBody, HttpServletResponse servletResponse)
	{
		final var responseData = authService.signIn(requestBody);
		
		final var cookie = new Cookie(ACCESS_TOKEN_COOKIE, responseData.getAccessToken());
		cookie.setPath("/");
		cookie.setMaxAge(ACCESS_TOKEN_DURATION_SECONDS);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		servletResponse.addCookie(cookie);

		return ResponseEntity.ok(responseData);
	}

	@PostMapping(path = SIGN_OUT_PATH)
	public ResponseEntity<Void> signOut(HttpServletResponse servletResponse)
	{
		final String accessToken = "";
		authService.signOut(accessToken);

		final var cookie = new Cookie(ACCESS_TOKEN_COOKIE, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		servletResponse.addCookie(cookie);

		return ResponseEntity.noContent().build();
	}
}
