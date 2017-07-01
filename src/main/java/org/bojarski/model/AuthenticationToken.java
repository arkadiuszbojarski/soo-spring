/**
 * 
 */
package org.bojarski.model;

import lombok.Data;

@Data
public class AuthenticationToken {
	private String access_token;
	private String token_type;
}
