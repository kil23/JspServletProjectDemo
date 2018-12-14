package com.myapp.util;

import javax.servlet.http.HttpServletRequest;

import com.myapp.app.form.Profileform;
import com.myapp.dao.model.Member.Type;

public class RegisterUtil {

	public Profileform getValues(HttpServletRequest request) {
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phone = request.getParameter("phone");
		Type type = Type.valueOf(request.getParameter("type"));
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("Cpassword");
		String yrExp = request.getParameter("yearExp");
		String epay = request.getParameter("pay");
		String noChild = request.getParameter("children");
		String spouse = request.getParameter("spouse");
		
		return new Profileform(fname, lname, phone, type, email, addr, password, cpassword, yrExp, epay, noChild, spouse);
	}
}
