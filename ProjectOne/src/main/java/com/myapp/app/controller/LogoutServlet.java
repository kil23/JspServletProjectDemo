package com.myapp.app.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie cookieUser = new Cookie("cookieUser", null);
	    Cookie cookieRemember = new Cookie("cookieRemember", null);
	    cookieUser.setMaxAge(0);
	    cookieRemember.setMaxAge(0);
	    response.addCookie(cookieUser);
	    response.addCookie(cookieRemember);
	    HttpSession httpSession = request.getSession();
	    httpSession.invalidate();
	    request.setAttribute("msg", "You have successfully logged out.");
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ProjectOne/jsp/index.jsp");
	    requestDispatcher.forward(request, response);
	  
	}
}
