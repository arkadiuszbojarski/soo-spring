/**
 * 
 */
package org.bojarski.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * Klasa dziedziny modelująca ilość.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@Data
public class Amount {

	@NotNull
	@Range(min = 0)
	private BigDecimal quantity;

	@NotBlank
	private String unit;
}
