package com.myapp.app.controller.sitter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dao.model.JobApplication;
import com.myapp.service.SitterService;
import com.myapp.util.ContextConnectionUtil;

public class DeleteJobAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		
		String jobAppid = request.getParameter("jobAppid");
		if(jobAppid!=null && jobAppid.trim().isEmpty() && jobAppid.matches("^[0-9]+$")) {
			int id = Integer.parseInt(jobAppid);
			JobApplication jobApplication = SitterService.getJobAppUsingJobAppid(id);
			if(id == jobApplication.getMemberid()) {
				boolean isDeleted = false;
				isDeleted = SitterService.deleteJobApp(id);
				if(isDeleted) {
					response.sendRedirect("/ProjectOne/jsp/sitter/listjobapplication?success=true");
				}
				else {
					response.sendRedirect("/ProjectOne/jsp/sitter/listjobapplication?success=false");
				}
			}else {
				response.sendRedirect("/ProjectOne/jsp/sitter/listjobapplication?success=false");
			}
		}
		else {
			response.sendRedirect("/ProjectOne/jsp/sitter/listjobapplication?success=false");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
