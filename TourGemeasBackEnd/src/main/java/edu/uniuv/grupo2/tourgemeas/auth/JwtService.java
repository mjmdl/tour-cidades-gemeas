package edu.uniuv.grupo2.tourgemeas.auth;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${security.jwt.secret}")
	private String JWT_SECRET;

	@Value("${security.jwt.duration-seconds}")
	private Long JWT_DURATION_SECONDS;

	public String sign(Map<String, String> claims, UserDetails userDetails) {
		return Jwts
			.builder()
			.setClaims(claims)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + JWT_DURATION_SECONDS * 1000))
			.signWith(getSignInKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean verify(String token, UserDetails userDetails) {
		Claims claims = claimsOf(token);
		return (
			(claims.getSubject().equals(userDetails.getUsername())) &&
			(claims.getExpiration().after(new Date()))
		);
	}

	public String usernameOf(String token) {
		return this
			.claimsOf(token)
			.getSubject();
	}

	private Claims claimsOf(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(getSignInKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
