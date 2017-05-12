/**
 * 
 */
package org.bojarski.service;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Arkadiusz Bojarski
 *
 */
public class TokenAuthenticationService {

	static final String SECRET = "secret";
	static final String PREFIX = "JWT";
	static final String RESPONSE_HEADER = "X-Auth-Token";
	static final String AUTHORIZATION_HEADER = "Authorization";

	public static void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder().setSubject(username).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		response.addHeader(RESPONSE_HEADER, new StringBuilder().append(PREFIX).append(' ').append(JWT.toString()).toString());

	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTHORIZATION_HEADER);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replaceAll(PREFIX, "")).getBody()
					.getSubject();
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}

		return null;
	}
}
