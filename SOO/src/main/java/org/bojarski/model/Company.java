/**
 * 
 */
package org.bojarski.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Data
@Document(collection = "companies")
public class Company {

	private static final int MAX_NAME_LENGTH = 100;
	private static final int MAX_ADDRESS_LENGTH = 500;

	@Id
	private String id;

	@NotBlank
	@Length(max = MAX_NAME_LENGTH)
	@Indexed(unique = true)
	private String name;

	@Length(max = MAX_ADDRESS_LENGTH)
	private String address;

}
