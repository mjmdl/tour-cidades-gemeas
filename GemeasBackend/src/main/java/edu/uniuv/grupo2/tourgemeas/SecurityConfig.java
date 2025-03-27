package edu.uniuv.grupo2.tourgemeas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.uniuv.grupo2.tourgemeas.auth.AuthController;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity
			.authorizeHttpRequests(requests -> requests
				.requestMatchers(HttpMethod.POST, AuthController.SIGN_UP_PATH, AuthController.SIGN_IN_PATH).permitAll()
				.anyRequest().authenticated())
			.csrf(csrf -> csrf.disable())
			.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
