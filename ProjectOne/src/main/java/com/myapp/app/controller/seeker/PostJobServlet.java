package com.myapp.app.controller.seeker;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.Jobform;
import com.myapp.service.SeekerService;
import com.myapp.util.ContextConnectionUtil;
import com.myapp.util.JobUtil;


public class PostJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/seeker/postjob.jsp");
        requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userid = ContextConnectionUtil.get().getMember().getId();
		JobUtil jobUtil = new JobUtil();
		Jobform jobform = jobUtil.getJobValues(request, userid);
		HashMap<String, String> errorJobMap = Jobform.AuthenticateJob();
		
		if(errorJobMap.isEmpty()) {
			boolean jobPosted = SeekerService.postJob(jobform);
			if(jobPosted) {
				 response.sendRedirect("/seeker/home?success=true");
			}else {
				response.sendRedirect("/seeker/home?success=false");
			}
		}
		else {
			request.setAttribute("job", errorJobMap);
			request.getRequestDispatcher("/jsp/seeker/homepage.jsp").forward(request, response);
		}
	}

}
