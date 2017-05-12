/**
 * 
 */
package org.bojarski.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;
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

	@Range(min = 0)
	private BigDecimal quantity;

	@NotEmpty
	private String unit;
}
