package com.myapp.app.controller.seeker;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.util.ContextConnectionUtil;


public class DeleteJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		String job_id = request.getParameter("Jobid");
		if(job_id!=null && !job_id.trim().isEmpty() && job_id.matches("^[0-9]+$")){
			int jobid = Integer.parseInt(job_id);
			boolean isDeleted = false;
			Job job = SeekerService.getJobByJobid(job_id);
			if(userid == job.getPostedBy()){
				isDeleted = SeekerService.deleteJob(jobid)
			}
			if(isDeleted){
				response.sendredirect("/ProjectOne/jsp/seeker/listjob?success=true");
			}else {
				response.sendredirect("/ProjectOne/jsp/seeker/listjob?success=false");
			}
		}
		
	}
}
