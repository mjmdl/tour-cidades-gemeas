package edu.uniuv.grupo2.tourgemeas.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {
	private static final String BEARER_PREFIX = "Bearer ";

	private final HandlerExceptionResolver handlerExceptionResolver;
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request, 
		@NonNull HttpServletResponse response, 
		@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		try {
			String token = getToken(request);
			if (token != null) {
				spawnAuthenticationToken(token, request);
			}
		} catch (Exception exception) {
			handlerExceptionResolver.resolveException(request, response, null, exception);
		}
		filterChain.doFilter(request, response);
		return;
	}

	private String getToken(HttpServletRequest request) {
		String authorization = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (AuthController.ACCESS_TOKEN_COOKIE.equals(cookie.getName())) {
					authorization = cookie.getValue();
					break;
				}
			}
		}
		if (authorization == null) {
			authorization = request.getHeader("Authorization");
			if (authorization == null) {
				return null;
			}
		}
		return authorization.substring(BEARER_PREFIX.length());
	}

	private void spawnAuthenticationToken(String token, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return;
		}

		String username = jwtService.usernameOf(token);
		if (username == null) {
			return;
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		if (!jwtService.verify(token, userDetails)) {
			return;
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails, 
			null, 
			userDetails.getAuthorities()
		);
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
