package edu.uniuv.grupo2.tourgemeas.auth;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.uniuv.grupo2.tourgemeas.exceptions.BadRequestException;
import edu.uniuv.grupo2.tourgemeas.exceptions.NotFoundException;
import edu.uniuv.grupo2.tourgemeas.user.User;
import edu.uniuv.grupo2.tourgemeas.user.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final AuthJwtService authJwtService;

	public SignUpDto.Res signUp(SignUpDto.Req request) throws BadRequestException
	{
		final var emailIsTaken = userRepository.existsByEmail(request.getEmail());
		if (emailIsTaken) throw new BadRequestException("O e-mail já está cadastrado.");
		
		final var user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setAdmin(false);

		final var savedUser = userRepository.save(user);
		return new SignUpDto.Res(savedUser.getId());
	}

	public SignInDto.Res signIn(SignInDto.Req request) throws NotFoundException
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final User user = userRepository.findByEmail(request.getUsername())
			.orElseThrow(() -> new NotFoundException("O usuário não foi encontrado."));
		final String accessToken = authJwtService.sign(new HashMap<>(), user);
		return new SignInDto.Res(accessToken);
	}

	public void signOut(String accessToken)
	{
	}
}
