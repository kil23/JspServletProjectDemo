package com.myapp.app.controller;

import javax.servlet.http.HttpServletRequest;

import com.myapp.app.form.Loginform;

public class LoginUtil {
	
	public static Loginform getLoginValues(HttpServletRequest request) {
		String user = request.getParameter("email");
		String passwd = request.getParameter("password");
		
		return new Loginform(user, passwd);
	}

}
