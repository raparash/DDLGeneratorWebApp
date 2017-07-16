/**
 * 
 */
package com.oracle.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.oracle.web.app.util.EmailUtil;

/**
 * @author raparash
 *
 */
public class EmailJunit {

	@Test
	public void testSendEmail(){
		List<String> to=new ArrayList<String>();
		to.add("rajuparashar23@gmail.com");
		EmailUtil.sendEmail(to, null, null, "aditi.chatterjee@oracle.com", "Test Subject", "Test Body", null);
	}
}
