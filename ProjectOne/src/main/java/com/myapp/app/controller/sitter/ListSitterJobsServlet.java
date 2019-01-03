package com.myapp.app.controller.sitter;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.app.form.Jobform;
import com.myapp.service.SitterService;
import com.myapp.util.ContextConnectionUtil;

public class ListSitterJobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = ContextConnectionUtil.get().getMember().getId();
		List<Jobform> listjob = SitterService.getJobListByUserid(userid);
		request.setAttribute("joblist", listjob);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ProjectOne/jsp/sitter/listjob.jsp");
		requestDispatcher.forward(request, response);
	}
}
