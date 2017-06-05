/**
 * 
 */
package org.bojarski.service;

import org.bojarski.model.Credentials;
import org.bojarski.model.User;
import org.bojarski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Service
public class MongoDBSignupService implements SignupService {

	private final UserRepository repository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	public MongoDBSignupService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean signup(Credentials credentials) {
		User user = new User();
		user.setName(credentials.getName());
		user.setPassword(encoder.encode(credentials.getPassword()));
		return repository.save(user) != null;
	}
}
