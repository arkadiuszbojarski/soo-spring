/**
 * 
 */
package org.bojarski.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Arkadiusz Bojarski
 *
 */
public class Credentials {

	@NotEmpty
	private String name;

	@NotEmpty
	@Length(min = 8, max = 32)
	private String password;

	/**
	 * 
	 */
	public Credentials() {}

	/**
	 * @param name
	 * @param password
	 */
	public Credentials(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Credentials [name=" + name + ", password=" + password + "]";
	}
}
