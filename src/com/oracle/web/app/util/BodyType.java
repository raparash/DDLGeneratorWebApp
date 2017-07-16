/**
 * 
 */
package com.oracle.web.app.util;

/**
 * @author raparash
 *
 */
public enum BodyType {

	TEXT("TEXT"),ATTACHMENT("ATTACHMENT");
	
	private String type;
	private BodyType(String type){
		this.setType(type);
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
