package edu.uniuv.grupo2.tourgemeas.auth;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.uniuv.grupo2.tourgemeas.HttpException;
import edu.uniuv.grupo2.tourgemeas.entities.User;
import edu.uniuv.grupo2.tourgemeas.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public SignUpResult signUp(SignUp signUpDto) {
		if (userRepository.existsByEmailIgnoreCase(signUpDto.getEmail())) {
			throw new HttpException(HttpStatus.CONFLICT, "EMAIL_USED", "O e-mail já está em uso.");
		}
		User user = new User();
		user.setName(signUpDto.getName());
		user.setEmail(signUpDto.getEmail().toLowerCase());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		user.setAdmin(signUpDto.getAdmin() != null ? signUpDto.getAdmin() : false);
		User savedUser = userRepository.save(user);
		return new SignUpResult(savedUser.getId());
	}

	public SignInResult signIn(SignIn signInDto) {
		User user = userRepository
			.findByEmailIgnoreCase(signInDto.getUsername())
			.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "USERNAME_NOT_FOUND", "Usuário não encontrado."));
		if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
			throw new HttpException(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "Usuário e senha estão incorretos.");
		}
		String token = jwtService.sign(new HashMap<>(), user);
		return new SignInResult(token, user.getAdmin());
	}
}
