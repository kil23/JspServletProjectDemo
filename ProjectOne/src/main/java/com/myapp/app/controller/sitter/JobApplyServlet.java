package com.myapp.app.controller.sitter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.JobAppform;
import com.myapp.dao.model.Job;
import com.myapp.dao.model.Sitter;
import com.myapp.service.SitterService;
import com.myapp.util.ContextConnectionUtil;
import com.myapp.util.PopulateForm;

public class JobApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		
		String jobid = request.getParameter("jobid");
		if(jobid != null && !jobid.trim().isEmpty() && jobid.matches("^[0-9]+$")) {
			int job_id = Integer.parseInt(jobid);
			Job job = SitterService.getJobByJobid(job_id);
			if(SitterService.isJobForUserid(userid, job_id)) {
				request.setAttribute("jobid", job_id);
				String jobtitle = job.getTitle();
				request.setAttribute("title", jobtitle);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/sitter/applytojob.jsp");
				requestDispatcher.forward(request, response);
			}
			else {
				response.sendRedirect("/ProjectOne/jsp/sitter/listjob.jsp");
			}
		}
		else {
			response.sendRedirect("/ProjectOne/jsp/sitter/listjob.jsp");
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userid = ContextConnectionUtil.get().getMember().getId();
		
		PopulateForm populateForm = new PopulateForm();
		JobAppform jobAppform = populateForm.getJobAppValues(request, userid);
		HashMap<String, String> errorPay= jobAppform.Authenticate();
		if(errorPay.isEmpty()) {
			boolean applied = SitterService.applyJob(jobAppform);
		}
	}

}
