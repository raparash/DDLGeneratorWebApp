/**
 * 
 */
package com.oracle.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.web.fmwk.RequestMapping;
import com.oracle.web.servlet.WebController;

/**
 * @author raparash
 *
 */
public class TestController implements WebController{

	@RequestMapping("/test.wss")
	public String show(HttpServletRequest request, HttpServletResponse response){
		System.out.println(request.getParameter("files"));
		
		return "test";
	}
}
