/**
 * 
 */
package com.oracle.tool.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.web.fmwk.RequestMapping;
import com.oracle.web.servlet.WebController;

/**
 * @author raparash
 *
 */
public class TestController implements WebController{
	
	@RequestMapping("/showDemo.wss")
	public String showDemo(HttpServletRequest request,HttpServletResponse response){
		return "This is a demo url";
	}

	@RequestMapping("/showBean.wss")
	public DemoBean showBean(HttpServletRequest request,HttpServletResponse response){
		DemoBean bean=new DemoBean();
		bean.setFirstName("Raju");
		bean.setLastName("Parashar");		
		return bean;
	}
	
}
