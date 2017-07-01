/**
 * 
 */
package org.bojarski.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class Settings {
    private Integer tokenExpirationTime;
    private String tokenSigningKey;
}
