package org.care.filters;


import org.care.context.MyApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
        IOException, ServletException {
        MyApplicationContext.create((HttpServletRequest) servletRequest);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(ConnectionSessionFilter.class.getName());
            logger.log(Level.SEVERE, "Exception generated in the fiter chain! ", e);
            servletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
        }
        MyApplicationContext.destroy();
    }

    @Override
    public void destroy() {

    }
}

