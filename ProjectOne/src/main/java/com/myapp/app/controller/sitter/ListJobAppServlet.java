package com.myapp.app.controller.sitter;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.JobAppform;
import com.myapp.service.SitterService;
import com.myapp.util.ContextConnectionUtil;

public class ListJobAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		String result = request.getParameter("success");
		if(result != null) {
			if(result.equalsIgnoreCase("true")) {
				request.setAttribute("resultmsg", "Operation is Successful");
			}else if(result.equalsIgnoreCase("false"))	{
				request.setAttribute("errormsg", "Opertion failed");
			}
		}
		
		List<JobAppform> listjobApp = SitterService.getJobApplicationListByUserid(userid);
		request.setAttribute("jobApp", listjobApp);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ProjectOne/jsp/sitter/listjobApplication.jsp");
		requestDispatcher.forward(request, response);
	}
}
