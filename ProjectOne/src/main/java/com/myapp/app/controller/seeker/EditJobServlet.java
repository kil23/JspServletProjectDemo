package com.myapp.app.controller.seeker;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.Jobform;
import com.myapp.service.SeekerService;
import com.myapp.util.ContextConnectionUtil;
import com.myapp.util.JobUtil;


public class EditJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		
		JobUtil jobutil = new JobUtil();
		Jobform jobform = jobutil.getJobValues(request, userid);
		HashMap<String, String> errorJobMap = Jobform.AuthenticateJob();
		
		if(errorJobMap.isEmpty()) {
			boolean update = SeekerService.updateJob(userid, jobform);
			if(update) {
				response.sendRedirect("/seeker/listjob?updated=true");
			}
			else {
				response.sendRedirect("/seeker/listjob?updated=false");
			}
		}
		else {
			request.setAttribute("job", errorJobMap);
			request.getRequestDispatcher("/jsp/seeker/homepage.jsp").forward(request, response);
		}
	}

}
