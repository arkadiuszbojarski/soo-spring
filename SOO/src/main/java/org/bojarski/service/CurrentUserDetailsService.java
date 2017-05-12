/**
 * 
 */
package org.bojarski.service;

import java.util.Optional;

import org.bojarski.model.CurrentUser;
import org.bojarski.model.User;
import org.bojarski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Component
public class CurrentUserDetailsService implements UserDetailsService {

	UserRepository repository;

	@Autowired
	public CurrentUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = Optional.ofNullable(repository.findOneByName(username))
				.orElseThrow(() -> new UsernameNotFoundException(username));	
		return new CurrentUser(user);
	}

}
