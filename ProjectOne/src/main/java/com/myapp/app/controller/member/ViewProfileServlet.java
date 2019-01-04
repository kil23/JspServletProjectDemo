package com.myapp.app.controller.member;

import java.io.IOException;
import java.lang.management.MemoryType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.Profileform;
import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Type;
import com.myapp.service.SeekerService;
import com.myapp.service.SitterService;
import com.myapp.util.ContextConnectionUtil;


public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		Type type = ContextConnectionUtil.get().getMember().getType();
		Profileform profiledata = null;
		if(type == Member.Type.Seeker) {
			profiledata = SeekerService.getProfiledataByUserid(userid);
		}else if(type == Member.Type.Sitter) {
			profiledata = SitterService.getProfiledataByUserid(userid);
		}else {
			response.sendRedirect("/ProjectOne");
		}
		request.setAttribute("Profiledata", profiledata);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/member/profile.jsp");
		requestDispatcher.forward(request, response);
	}
}
 