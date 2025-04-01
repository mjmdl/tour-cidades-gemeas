package edu.uniuv.grupo2.tourgemeas.auth;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AuthJwtFilter extends OncePerRequestFilter {
	private final String BEARER_PREFIX = "Bearer ";

	private final HandlerExceptionResolver handlerExceptionResolver;
	private final AuthJwtService authJwtService;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException 
	{
		final var authorization = request.getHeader("Authorization");

		if (authorization == null || !authorization.startsWith(BEARER_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}

		try {
			spawnAuthenticationToken(authorization, request);
			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			handlerExceptionResolver.resolveException(request, response, null, exception);
		}
	}

	private void spawnAuthenticationToken(String authorization, HttpServletRequest request)
	{
		final var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) return;

		final var token = authorization.substring(BEARER_PREFIX.length());
		final var email = authJwtService.usernameOf(token);
		if (email == null) return;

		final var userDetails = this.userDetailsService.loadUserByUsername(email);
		if (!authJwtService.isValid(token, userDetails)) return;

		final var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
