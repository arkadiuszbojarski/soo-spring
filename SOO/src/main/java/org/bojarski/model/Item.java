/**
 * 
 */
package org.bojarski.model;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Data
public class Item {
	
	private static final int MAX_FIELD_LENGTH_100 = 100;
	private static final int MAX_FIELD_LENGTH_500 = 500;

	@NotEmpty
	@Length(max = MAX_FIELD_LENGTH_100)
	private String name;
	
	@Length(max = MAX_FIELD_LENGTH_500)
	private String comment;
	
	@Valid
	private Amount amount;
	
	@Range(min = 0)
	private BigDecimal price;
	
	@Valid
	private Duration realization;
}
