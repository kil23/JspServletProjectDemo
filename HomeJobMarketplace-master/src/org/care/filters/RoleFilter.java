/*
 * Copyright 2006-2018 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package org.care.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.care.context.MyApplicationContext;
import org.care.model.Member;

/**
 * Created 9/6/2018 11:20 PM
 *
 */
public class RoleFilter implements Filter {

  @Override public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override public void doFilter(ServletRequest servletRequest,
      ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    boolean hasErrors = false;
    if(httpRequest.getRequestURI().contains("/member")) {
      hasErrors = MyApplicationContext.get().getMember() == null;
    } else if (httpRequest.getRequestURI().contains("/seeker")) {
      Member member = MyApplicationContext.get().getMember();
      hasErrors = member == null || member.getType() != Member.MemberType.SEEKER;
    } else if (httpRequest.getRequestURI().contains("/sitter")) {
      Member member = MyApplicationContext.get().getMember();
      hasErrors = member == null || member.getType() != Member.MemberType.SITTER;
    }
    if(hasErrors) {
      httpRequest.getRequestDispatcher("/WEB-INF/jsp/404error.jsp").forward(servletRequest, servletResponse);
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  @Override public void destroy() {
  }
}
