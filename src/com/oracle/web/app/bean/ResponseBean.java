/**
 * 
 */
package com.oracle.web.app.bean;

import com.oracle.web.fmwk.JsonBean;

/**
 * @author raparash
 *
 */
public class ResponseBean implements JsonBean{

	private int responsecode;
	private String message;
	/**
	 * @return the responsecode
	 */
	public int getResponsecode() {
		return responsecode;
	}
	/**
	 * @param responsecode the responsecode to set
	 */
	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
