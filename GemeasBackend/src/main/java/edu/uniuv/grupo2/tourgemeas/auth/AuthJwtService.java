package edu.uniuv.grupo2.tourgemeas.auth;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthJwtService {
	@Value("${security.jwt.secret}")
	private final String JWT_SECRET = null;

	@Value("${security.jwt.duration-seconds}")
	private final long JWT_DURATION_SECONDS = 0L;

	public String sign(Map<String, Object> claims, UserDetails userDetails)
	{
		final var now = System.currentTimeMillis();
		return Jwts
			.builder()
			.setClaims(claims)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + JWT_DURATION_SECONDS * 1000))
			.signWith(getSignInKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean isValid(String token, UserDetails userDetails)
	{
		final String username = usernameOf(token);
		return (username.equals(userDetails.getUsername())) && !isExpired(token);
	}

	public String usernameOf(String token)
	{
		return claimOf(token, Claims::getSubject);
	}

	public boolean isExpired(String token)
	{
		final var now = new Date();
		return expirationOf(token).before(now);
	}

	public Date expirationOf(String token)
	{
		return claimOf(token, Claims::getExpiration);
	}

	public <T> T claimOf(String token, Function<Claims, T> resolver)
	{
		final var claims = claimsOf(token);
		return resolver.apply(claims);
	}

	private Claims claimsOf(String token)
	{
		return Jwts
			.parserBuilder()
			.setSigningKey(getSignInKey())
			.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInKey()
	{
		byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
