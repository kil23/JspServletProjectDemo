package com.myapp.app.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.app.form.Registerform;
import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Type;
import com.myapp.service.MemberService;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		RegisterUtil registerUtil = new RegisterUtil();
		Registerform registerform = registerUtil.getValues(request);
		HashMap<String, String> errorRegMap = registerform.validate();
		
		if(errorRegMap.isEmpty()) {
			Member mem = MemberService.insertNewUser(registerform);
			HttpSession session = request.getSession();
			session.setAttribute("user", mem);
			session.setAttribute("username", mem.getFname());
			Type type = MemberService.checkType(mem);
			if(type==Type.valueOf("Seeker")) {
				response.sendRedirect("/seeker/homepage.jsp");
			}
			else {
				response.sendRedirect("/sitter/homepage.jsp");
			}
		}else {
			request.setAttribute("errorsReg", errorRegMap);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
			rd.forward(request, response);
		}
	}

}
