/**
 * 
 */
package org.bojarski.service;

import java.util.Optional;

import org.bojarski.model.AccountDetails;
import org.bojarski.model.User;
import org.bojarski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Service
public class MongoDBUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	/**
	 * @param repository
	 */
	@Autowired
	public MongoDBUserDetailsService(UserRepository repository) {
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
		return new AccountDetails(user);
	}
}
