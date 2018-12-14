package com.myapp.app.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.service.MemberService;

public class DeleteProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		boolean isDeleted = MemberService.deleteUser(id);
		
		if(isDeleted) {
			session.invalidate();
			response.sendRedirect("/ProjectOne/");
		}
		else {
			request.setAttribute("error", "Cannot delete user profile.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/member/profile.jsp");
			dispatcher.forward(request, response);			
		}
	}
}
