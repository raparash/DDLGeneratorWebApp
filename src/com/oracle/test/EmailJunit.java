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
		to.add("raju.parashar@oracle.com");
		
		List<String> cc=new ArrayList<String>();
		cc.add("raju.parashar@oracle.com");
		
		List<String> bcc=new ArrayList<String>();
		bcc.add("aditi.chatterjee@oracle.com");
		
		
		List<String> attachments=new ArrayList<String>();
		attachments.add("D:/JiraSubtaskTemplate.xlsx");
		
		String from="raghav.nirwani@oracle.com";
		
		
		EmailUtil.sendEmail(to, cc, null, from, "Test Subject", "Dummy Body", attachments);
	}
}
