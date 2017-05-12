/**
 * 
 */
package org.bojarski.configuration;

import org.bojarski.filter.AuthenticationFilter;
import org.bojarski.filter.LoginFilter;
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

	/**
	 * 
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.cors().and()
		.httpBasic().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers(HttpMethod.POST, "/signup").permitAll()
		// .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
		.anyRequest().authenticated();
		// .addFilterBefore(new LoginFilter("/authenticate", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		// .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
