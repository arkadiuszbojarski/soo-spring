/**
 * 
 */
package org.bojarski.controller;

import org.bojarski.Constants;
import org.bojarski.model.Credentials;
import org.bojarski.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User registration controller class.
 * @author Arkadiusz Bojarski
 *
 */
@RestController
public class SignupController {

	private final SignupService service;

	/**
	 * 
	 * @param service handling user registrations.
	 */
	@Autowired
	public SignupController(SignupService service) {
		this.service = service;
	}

	/**
	 * Method allowing for user registrations.
	 * 
	 * @param credentials used to log onto newly created account.
	 * @return response containing confirmation message.
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/signup")
	public String signup(@RequestBody Credentials credentials) {
		return service.signup(credentials) ? Constants.ACCOUNT_CREATION_SUCCESS : Constants.ACCOUNT_CREATION_FAIL;
	}
}
