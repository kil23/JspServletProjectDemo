package com.myapp.app.controller.seeker;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.JobAppform;
import com.myapp.dao.model.Job;
import com.myapp.service.SeekerService;
import com.myapp.util.ContextConnectionUtil;

public class ListSeekerJobAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int userid = ContextConnectionUtil.get().getMember().getId();
		String jobid = request.getParameter("JobId");
		if(jobid !=null && !jobid.trim().isEmpty() && jobid.matches("^[0-9]+$")) {
			int job_id = Integer.parseInt(jobid);
			Job job = SeekerService.getJobByJobid(job_id);
			if(userid == job.getPostedBy()) {
				String title = job.getTitle();
				String name = SeekerService.getNameByid(userid);
				
				List<JobAppform> seekerJobApp = SeekerService.getJobAppByJobid(job_id);
				request.setAttribute("jobApp", seekerJobApp);
				request.setAttribute("jobtitle", title);
				request.setAttribute("name", name);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/seeker/listjobapp.jsp");
				dispatcher.forward(request, response);
			}
			else {
				response.sendRedirect("/ProjectOne/jsp/seeker/listjob?success=false");
			}
		}
		else {
			response.sendRedirect("/ProjectOne/jsp/seeker/listjob?success=false");
		}
	}
}
