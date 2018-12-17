package com.myapp.app.controller.seeker;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.Jobform;
import com.myapp.service.SeekerService;
import com.myapp.util.ContextConnectionUtil;


public class ListSeekerJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = ContextConnectionUtil.get().getMember().getId();
		
		String result = request.getParameter("success");
		if(result!=null) {
			if(result.toLowerCase().equals("true")) {
				request.setAttribute("success", "Operation completed!");
			}else {
				request.setAttribute("failed", "Operation failed!");
			}
		}
		
		List<Jobform> jobs = SeekerService.getJobList(userId);
		request.setAttribute("JobList", jobs);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/seeker/joblist.jsp");
		requestDispatcher.forward(request, response);
	}

}
