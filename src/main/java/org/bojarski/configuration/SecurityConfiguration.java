/**
 * 
 */
package org.bojarski.configuration;

import org.bojarski.filter.AuthenticationFilter;
import org.bojarski.filter.LoginFilter;
import org.bojarski.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Klasa konfiguracyjna odpowiedzialna za ustawienie bezpieczeństwa aplikacji.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@Configuration
@EnableGlobalMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final TokenAuthenticationService tokenAuthenticationService;

	/**
	 * @param userDetailsService
	 */
	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService, TokenAuthenticationService tokenAuthenticationService) {
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.cors().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers(HttpMethod.POST, "/signup").permitAll()
		.antMatchers(HttpMethod.POST, "/authenticate").permitAll()
		.anyRequest().authenticated().and()
		.addFilterBefore(new LoginFilter("/authenticate", authenticationManager(), tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new AuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
	}
}
