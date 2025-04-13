package edu.uniuv.grupo2.tourgemeas.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.uniuv.grupo2.tourgemeas.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class AuthConfig {
	private final UserRepository userRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository
			.findByEmailIgnoreCase(username)
			.orElseThrow(() -> new UsernameNotFoundException(("O usuário não foi encontrado.")));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final var provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}
