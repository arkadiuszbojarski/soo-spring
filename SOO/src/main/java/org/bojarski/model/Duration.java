/**
 * 
 */
package org.bojarski.model;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * @author Arkadiusz Bojarski
 *
 */
@Data
public class Duration {

	@Range(min = 0)
	private int months;
	
	@Range(min = 0)
	private int days;
	
	@Range(min = 0)
	private int hours;
	
	@Range(min = 0)
	private int minutes;
}
