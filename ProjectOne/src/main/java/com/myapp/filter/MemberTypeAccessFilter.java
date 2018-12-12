package com.myapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dao.model.Member;
import com.myapp.util.ContextConnectionUtil;


public class MemberTypeAccessFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestDispatcher dispatcher;
		boolean hasErrors = false;
		if(httpServletRequest.getRequestURI().contains("/visitor")) {
			hasErrors = ContextConnectionUtil.get().getMember()==null;
			if(hasErrors) {
				dispatcher = request.getRequestDispatcher("/ProjectOne/jsp/visitor/index.jsp");
				dispatcher.forward(request, response);
			}
		}else if(httpServletRequest.getRequestURI().contains("/seeker")) {
			Member member = ContextConnectionUtil.get().getMember();
			hasErrors = member == null || member.getType() != Member.Type.Seeker;
			if(hasErrors) {
				dispatcher = request.getRequestDispatcher("/ProjectOne/jsp/seeker/homepage.jsp");
				dispatcher.forward(request, response);
			}
		}else if(httpServletRequest.getRequestURI().contains("/sitter")) {
			Member member = ContextConnectionUtil.get().getMember();
			hasErrors = member == null || member.getType() != Member.Type.Sitter;
			if(hasErrors) {
				dispatcher = request.getRequestDispatcher("/ProjectOne/jsp/sitter/homepage.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
