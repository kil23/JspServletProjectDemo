package com.myapp.app.controller.sitter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.JobAppform;
import com.myapp.dao.model.JobApplication;
import com.myapp.service.SitterService;
import com.myapp.util.ContextConnectionUtil;
import com.myapp.util.PopulateForm;

public class EditJobAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		String jobApp_id = request.getParameter("jobAppid");
		if(jobApp_id!=null && !jobApp_id.trim().isEmpty() && jobApp_id.matches("^[0-9]+$")){
		    int jobAppid = Integer.parseInt(jobApp_id);
		    JobApplication jobApplication = SitterService.getJobAppUsingJobAppid(jobAppid);
		    if(userid == jobApplication.getMemberid()){
                String title = SitterService.getJobApplicationTitleByJobAppid(jobAppid);
                request.setAttribute("jobApplicationid", jobAppid);
                request.setAttribute("title", title);
                request.setAttribute("expectedPay", jobApplication.getExpectedPay());
                RequestDispatcher requesrDispatcher = request.getRequestDispatcher("/ProjectOne/jsp/sitter/editjobApp.jsp");
                requesrDispatcher.forward(request, response);
            }else{
		        response.sendRedirect("/ProjectOne/jsp/sitter/listjobApp?success=false");
            }
        }else{
            response.sendRedirect("/ProjectOne/jsp/sitter/listjobApp?success=false");
        }
 	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = ContextConnectionUtil.get().getMember().getId();
        PopulateForm populateForm = new PopulateForm();
	    JobAppform jobAppForm = populateForm.getJobAppValues(request, userid);
	    boolean isUpdated = SitterService.updateJobApplication(jobAppForm);
	    if(isUpdated){
	        response.sendRedirect("/ProjectOne/jsp/sitter/listjobApp?success=true");
        }else{
	        response.sendRedirect("/ProjectOne/jsp/sitter/listjobApp?success=false");
        }
	}

}
