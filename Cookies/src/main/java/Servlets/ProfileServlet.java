package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		request.getRequestDispatcher("link.html").include(request, response);
		
		Cookie ck[] = request.getCookies();
		if(ck!=null) {
			String name = ck[0].getValue();
			if(!name.equals("")||name!=null) {
				out.println("<pre>");
				out.println("***<b>Welcome to Profile</b>***");
				out.println("Welcome, "+name);
				out.println("</pre>");
			}
		}else {
			out.println("Please login first...");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}
}
