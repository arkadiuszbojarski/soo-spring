/**
 * 
 */
package org.bojarski.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Data
public class Item {

	private static final int MAX_FIELD_LENGTH_500 = 500;

	@NotBlank
	@Length(max = MAX_FIELD_LENGTH_500)
	private String name;

	private String comment;

	@NotNull
	@Valid
	private Amount amount;

	@NotNull
	@Range(min = 0)
	private BigDecimal price;

	@Valid
	private Duration realization;
}
