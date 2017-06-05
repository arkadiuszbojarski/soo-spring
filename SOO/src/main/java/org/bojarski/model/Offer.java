/**
 * 
 */
package org.bojarski.model;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Data
@Document(collection = "offers")
public class Offer {
	
	private static final int MAX_FIELD_LENGTH_100 = 100;
	private static final int MAX_FIELD_LENGTH_500 = 500;

	@Id
	private String id;
	
	@NotEmpty
	@Length(max = MAX_FIELD_LENGTH_100)
	@Indexed(unique = true)
	private String number;
	
	@Length(max = MAX_FIELD_LENGTH_100)
	private String basis;
	
	@Length(max = MAX_FIELD_LENGTH_500)
	private String payment;
	
	@Length(max =MAX_FIELD_LENGTH_500)
	private String delivery;
	
	@Length(max = MAX_FIELD_LENGTH_500)
	private String comment;

	private String recipient;

	@CreatedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date created;
	
	@Future
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date validity;
	
	@Valid
	private Collection<Item> items;
}
