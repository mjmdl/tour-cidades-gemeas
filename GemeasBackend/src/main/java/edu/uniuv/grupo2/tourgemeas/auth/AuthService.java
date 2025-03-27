package edu.uniuv.grupo2.tourgemeas.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.uniuv.grupo2.tourgemeas.exceptions.BadRequestException;
import edu.uniuv.grupo2.tourgemeas.exceptions.NotFoundException;
import edu.uniuv.grupo2.tourgemeas.exceptions.UnauthorizedException;
import edu.uniuv.grupo2.tourgemeas.user.User;
import edu.uniuv.grupo2.tourgemeas.user.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

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
		final var user = userRepository.findByEmail(request.getUsername());
		if (user == null) throw new NotFoundException("O usuário não foi encontrado.");
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new UnauthorizedException("O usuário ou a senha está(ão) errado(s).");

		return new SignInDto.Res("token-de-acesso");
	}

	public void signOut(String accessToken)
	{
	}
}
