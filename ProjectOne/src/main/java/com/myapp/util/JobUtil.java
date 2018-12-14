package com.myapp.util;

import javax.servlet.http.HttpServletRequest;

import com.myapp.app.form.Jobform;

public class JobUtil {
	
	public Jobform getJobValues(HttpServletRequest request, int userid) {
		String title = request.getParameter("title");
		int postedBy = userid;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String payPerHr = request.getParameter("payPerHr");
		
		return new Jobform(title, postedBy, startDate, endDate, payPerHr);
		
	}
}
