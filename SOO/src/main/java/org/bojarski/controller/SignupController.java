/**
 * 
 */
package org.bojarski.controller;

import org.bojarski.Messages;
import org.bojarski.model.Credentials;
import org.bojarski.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Klasa kontrolera rejestracja użytkownika.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@RestController
public class SignupController {

	private final SignupService service;

	/**
	 * Konstruktor zapamiętujący referencję do {@link SignupService}
	 * 
	 * @param service
	 *            serwis obsługujący rejestrację użytkownika.
	 */
	@Autowired
	public SignupController(SignupService service) {
		this.service = service;
	}

	/**
	 * Metoda pozwalająca na zarejestrowania nowego konta użytkownika.
	 * 
	 * @param credentials
	 *            dane wykorzystywane do logowania na nowo tworzone konto
	 *            użytkownika.
	 * @return odpowiedź zawierająca potwierdzenie utworzenie nowego konta
	 *         użytkownika.
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/signup")
	public String signup(@RequestBody Credentials credentials) {
		return service.signup(credentials) ? Messages.ACCOUNT_CREATION_SUCCESS : Messages.ACCOUNT_CREATION_FAIL;
	}
}
