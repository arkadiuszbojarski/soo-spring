/**
 * 
 */
package org.bojarski.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bojarski.model.Credentials;
import org.bojarski.service.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa bezstanowego filtru logowania.
 * 
 * @author Arkadiusz Bojarski
 *
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService service;

	/**
	 * Konstruktor zapamiętujący podane parametry.
	 * 
	 * @param url
	 *            napis stanowiący adres pod którym aplikacja będzie oczekiwać
	 *            na zapytania zawierające dane uwierzytelniające.
	 * @param authenticationManager
	 *            {@link AuthenticationManager} obsługujący właściwą walidację
	 *            danych uwierzytelniających.
	 * @param tokenAuthenticationService
	 */
	public LoginFilter(String url, AuthenticationManager authenticationManager,
			TokenAuthenticationService tokenAuthenticationService) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
		this.service = tokenAuthenticationService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		final Credentials login = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);

		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		service.addAuthentication(response, authResult.getName());
	}
}
