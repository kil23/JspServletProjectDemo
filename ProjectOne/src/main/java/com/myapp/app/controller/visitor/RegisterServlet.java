package com.myapp.app.controller.visitor;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.app.form.Profileform;
import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Type;
import com.myapp.dao.model.Seeker;
import com.myapp.dao.model.Sitter;
import com.myapp.service.MemberService;
import com.myapp.util.RegisterUtil;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		RegisterUtil registerUtil = new RegisterUtil();
		Profileform profileform = registerUtil.getValues(request);
		HashMap<String, String> errorRegMap = profileform.Authenticate();
		
		if(errorRegMap.isEmpty()) {
			Member mem = MemberService.insertNewMember(profileform);
			int memid = mem.getId();
			Type type = MemberService.checkType(mem);
			HttpSession session = request.getSession();
			if(type==Type.valueOf("Seeker")) {
				Seeker seeker = MemberService.insertNewSeeker(memid, profileform);
				session.setAttribute("user", seeker);
				session.setAttribute("username", seeker.getFname());
				session.setAttribute("id", seeker.getId());
				session.setAttribute("type", seeker.getType());
				response.sendRedirect("/ProjectOne/jsp/seeker/homepage.jsp");
			}
			else {
				 Sitter sitter = MemberService.insertNewSitter(memid, profileform);
				 session.setAttribute("user", sitter);
				 session.setAttribute("username", sitter.getFname());
				 session.setAttribute("id", sitter.getId());
				 session.setAttribute("type", sitter.getType());
				response.sendRedirect("/ProjectOne/jsp/sitter/homepage.jsp");
			}
		}else {
			request.setAttribute("errorsReg", errorRegMap);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/visitor/register.jsp");
			rd.forward(request, response);
		}
	}

}
