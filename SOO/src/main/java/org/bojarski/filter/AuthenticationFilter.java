/**
 * 
 */
package org.bojarski.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.bojarski.service.TokenAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Klasa bezstanowego filtru uwierzytelniania.
 * 
 * @author Arkadiusz Bojarski
 *
 */
public class AuthenticationFilter extends GenericFilterBean {
	
	private final TokenAuthenticationService service;
	
	/**
	 * @param tokenAuthenticationService
	 */
	public AuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
		this.service = tokenAuthenticationService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		Authentication authentication = service.getAuthentication((HttpServletRequest) request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filter.doFilter(request, response);
	}
}
