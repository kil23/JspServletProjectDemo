package com.myapp.app.controller;

import javax.servlet.http.HttpServletRequest;

import com.myapp.app.form.Registerform;
import com.myapp.dao.model.Member.Type;

public class RegisterUtil {

	public Registerform getValues(HttpServletRequest request) {
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phone = request.getParameter("phone");
		Type type = Type.valueOf(request.getParameter("type"));
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("Cpassword");
		
		return new Registerform(fname, lname, phone, type, email, addr, password, cpassword);
	}
}
