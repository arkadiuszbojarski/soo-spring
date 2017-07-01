package org.bojarski.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Class modeling user account.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@Data
@Document(collection = "users")
public class User {

	@Id
	private String id;

	@NotEmpty
	@Indexed(unique = true)
	private String name;

	@JsonIgnore
	@NotEmpty
	@Length(min = 8, max = 32)
	private String password;
}
