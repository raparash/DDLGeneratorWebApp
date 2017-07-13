/**
 * 
 */
package com.oracle.tool.controller;

import com.oracle.web.fmwk.JsonBean;

/**
 * @author raparash
 *
 */
public class DemoBean implements JsonBean {

	private String firstName;
	private String lastName;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
