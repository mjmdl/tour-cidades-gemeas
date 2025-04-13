package edu.uniuv.grupo2.tourgemeas.auth;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import edu.uniuv.grupo2.tourgemeas.HttpException;
import edu.uniuv.grupo2.tourgemeas.entities.User;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AdminOnlyAspect {
	@Before("@annotation(edu.uniuv.grupo2.tourgemeas.auth.AdminOnly)")
	public void checkAdminAccess() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			throw new HttpException(HttpStatus.UNAUTHORIZED, "UNAUTHENTICATED", "É necessário estar logado para acessar o recurso.");
		}
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (userDetails instanceof User user && !user.getAdmin()) {
			throw new HttpException(HttpStatus.FORBIDDEN, "UNAUTHORIZED", "É necessário ter permissão de administrador para acessar o recurso.");
		}
	}
}
