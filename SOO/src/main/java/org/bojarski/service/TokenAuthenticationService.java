/**
 * 
 */
package org.bojarski.service;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bojarski.model.User;
import org.bojarski.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Service
public class TokenAuthenticationService {

	private static final String SECRET = "secret";
	private static final String PREFIX = "JWT";
	private static final String RESPONSE_HEADER = "X-Auth-Token";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	private final UserRepository repository;
	
	/**
	 * @param repository
	 */
	public TokenAuthenticationService(UserRepository repository) {
		this.repository = repository;
	}

	public void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder().setSubject(username).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		response.addHeader(RESPONSE_HEADER, new StringBuilder().append(PREFIX).append(' ').append(JWT.toString()).toString());
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTHORIZATION_HEADER);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replaceAll(PREFIX, "")).getBody().getSubject();
			if (user != null) {
				final User retreived = repository.findOneByName(user);
				if(retreived != null) {
					return new UsernamePasswordAuthenticationToken(retreived.getName(), null, Collections.emptyList());
				}
			}
		}
		return null;
	}
}
