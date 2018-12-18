package com.myapp.app.controller.seeker;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeekerHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String success = request.getParameter("success");
        if(success != null) {
            if(success.equalsIgnoreCase("true")) {
                request.setAttribute("success", "Operation Completed!");
            } else if (success.equalsIgnoreCase("false")) {
                request.setAttribute("fail", "Operation Failed!");
            }
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/seeker/homepage.jsp");
        requestDispatcher.forward(request, response);
	}
}
