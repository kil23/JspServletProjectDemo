package com.myapp.app.controller.member;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.Profileform;
import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Type;
import com.myapp.service.MemberService;
import com.myapp.util.ContextConnectionUtil;
import com.myapp.util.RegisterUtil;

public class SaveProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterUtil registerUtil = new RegisterUtil();
		Profileform registerform = registerUtil.getValues(request);
		HashMap<String, String> errorRegMap = registerform.Authenticate();
		
		Type type = ContextConnectionUtil.get().getMember().getType();
		
		if(errorRegMap.isEmpty()) {
			if(type == Member.Type.Sitter) {
				MemberService.updateSitterInfo(registerform);
				response.sendRedirect("/ProjectOne/jsp/sitter/homepage.jsp");
			}
			else {
				MemberService.updateSeekerInfo(registerform);
				response.sendRedirect("/ProjectOne/jsp/seeker/homepage.jsp");
			}
		}
		else {
			request.setAttribute("errorsReg", errorRegMap);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/editprofile.jsp");
			rd.forward(request, response);
		}
	}

}
