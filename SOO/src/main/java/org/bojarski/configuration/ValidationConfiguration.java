/**
 * 
 */
package org.bojarski.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;

/**
 * Klasa konfiguracyjna odpowiedzialna za ustawienie metod walidacji danych przetwarzanych przez aplikację.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@Configuration
public class ValidationConfiguration extends RepositoryRestConfigurerAdapter {

	@Qualifier("jsr303Validator")
	private final Validator validator;
	
	/**
	 * 
	 * @param validator
	 */
	@Autowired
	public ValidationConfiguration(Validator validator) {
		this.validator = validator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.rest.webmvc.config.
	 * RepositoryRestConfigurerAdapter#
	 * configureValidatingRepositoryEventListener(org.springframework.data.rest.
	 * core.event.ValidatingRepositoryEventListener)
	 */
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", validator);
		validatingListener.addValidator("beforeSave", validator);
	}
}
