package com.myapp.util;

import com.myapp.app.form.Loginform;
import javax.servlet.http.HttpServletRequest;

public class LoginUtil {
	
	public static Loginform getLoginValues(HttpServletRequest request) {
		String user = request.getParameter("email");
		String passwd = request.getParameter("password");
		return new Loginform(user, passwd);
	}

}
