/**
 * 
 */
package org.bojarski.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Data
public class Credentials {

	@NotEmpty
	private String name;

	@NotEmpty
	@Length(min = 8, max = 32)
	private String password;
}
