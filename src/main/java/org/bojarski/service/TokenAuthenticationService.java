/**
 * 
 */
package org.bojarski.service;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bojarski.Constants;
import org.bojarski.configuration.Settings;
import org.bojarski.model.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Service
public class TokenAuthenticationService {
	
	private final Settings settings;
	
	@Autowired
	public TokenAuthenticationService(Settings settings) {
		this.settings = settings;
	}

	public void addAuthentication(HttpServletResponse response, Authentication authentication) throws JsonProcessingException, IOException {
		Instant current = Instant.now();
		
		String JWT = Jwts.builder()
				.setSubject(authentication.getName())
				.setIssuedAt(Date.from(current))
				.setExpiration(Date.from(current.plus(settings.getTokenExpirationTime(), ChronoUnit.MINUTES)))
				.signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
			.compact();
		
		AuthenticationToken token = new AuthenticationToken();
		token.setAccess_token(JWT);
		token.setToken_type(Constants.BEARER);
		
		ObjectMapper mapper = new ObjectMapper();

		response.setHeader(Constants.CONTENT_TYPE_HEADER, Constants.CONTENT_TYPE_JSON);
		response.getWriter().write(mapper.writeValueAsString(token));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String payload = request.getHeader(Constants.SECURITY_AUTHORIZATION_HEADER);
		if (payload != null) {
			Claims claims = Jwts.parser()
					.setSigningKey(settings.getTokenSigningKey())
					.parseClaimsJws(payload.replaceAll(Constants.SECURITY_PREFIX, ""))
				.getBody();
			return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.emptyList());	
		}
		return null;
	}
}
