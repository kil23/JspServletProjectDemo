package com.myapp.util;

import javax.servlet.http.HttpServletRequest;

import com.myapp.app.form.JobAppform;
import com.myapp.app.form.Loginform;
import com.myapp.dao.model.JobApplication.Status;

public class PopulateForm {
	
	public JobAppform getJobAppValues(HttpServletRequest request, int userid) {
		String jobid = request.getParameter("jobid");
		String expectedPay = request.getParameter("expectedpay");
		Status status = Status.valueOf("Active");
		String user_id = new Integer(userid).toString();
		return new JobAppform(jobid, user_id, expectedPay, status);
	}
	
	public static Loginform getLoginValues(HttpServletRequest request) {
		String user = request.getParameter("email");
		String passwd = request.getParameter("password");
		return new Loginform(user, passwd);
	}

}
