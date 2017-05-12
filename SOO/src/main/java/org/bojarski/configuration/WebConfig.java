/**
 * 
 */
package org.bojarski.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Klasa konfiguracyjna odpowiedzialna za ustawienia sieciowe aplikacji.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*").allowedOrigins("*");
	}
}
