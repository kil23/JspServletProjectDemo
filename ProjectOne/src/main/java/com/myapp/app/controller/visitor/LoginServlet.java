package com.myapp.app.controller.visitor;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.app.form.Loginform;
import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Type;
import com.myapp.service.MemberService;
import com.myapp.util.LoginUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Loginform loginform = LoginUtil.getLoginValues(request);
		
		HashMap<String, String> errorLoginMap = loginform.validate();
		
		if(errorLoginMap.isEmpty()) {
			String email = loginform.getEmail();
			String password = loginform.getPassword();		
			
			Member member = MemberService.existingUserCheck(email, password);
			
			if (request.getParameter("remember") != null) {
				String remember = request.getParameter("remember");
				Cookie cookieUser = new Cookie("cookuser", email.trim());
				Cookie cookieRemember = new Cookie("cookrem", remember.trim());
				cookieUser.setMaxAge(60 * 60 * 24 * 15);
				cookieRemember.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(cookieUser);
				response.addCookie(cookieRemember);
		    } 
			
			HttpSession httpSession = request.getSession();
	        httpSession.setAttribute("user", email.trim());
			
			if(member != null) {
				Type type = member.getType();
				if(type == Type.valueOf("Sitter")) {
					response.sendRedirect("/sitter/homepage.jsp");
				}else {
					response.sendRedirect("/seeker/homepage.jsp");
				}
			}else {
				request.setAttribute("errorsLogin", errorLoginMap);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
			}
			
		}else {
			request.setAttribute("errorsLogin", errorLoginMap);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
		}		
	}
}
