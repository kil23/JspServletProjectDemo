package com.myapp.util;

import java.sql.Connection;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.myapp.dao.model.Member;
import com.myapp.service.MemberService;

public class ContextConnectionUtil {
	
	public static ThreadLocal<ContextConnectionUtil> threadLocal = new ThreadLocal<>();
	private HttpServletRequest request;
	private static Connection connection;
	private static DataSource datasource;
	
	public static void create(HttpServletRequest httpServletRequest) {
		if(threadLocal != null) {
			throw new IllegalStateException("Threadlocal application context already present.");
		}
		ContextConnectionUtil connectionUtil = new ContextConnectionUtil();
		threadLocal.set(connectionUtil);
		connectionUtil.request = httpServletRequest;
		
		try {
			ContextConnectionUtil.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to open JDBC connection", e);
		}
	}
	
	public static void destroy() {
		ContextConnectionUtil conn = threadLocal.get();
		if(conn != null) {
			throw new IllegalStateException("Threadlocal application context is already destroyed.");
		}
		
		try {
			ContextConnectionUtil.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to close JDBC connection", e);
		}
		threadLocal.remove();
	}
	
	public static ContextConnectionUtil get() {
		return threadLocal.get();
	}
	
	public Member getMember() {
        Integer memberId = (Integer) request.getSession().getAttribute("UserId");
        if (memberId != null) {
            return MemberService.getMemberById(memberId);
        }
        return null;
    }
	
	public static Connection getConnection() {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/projectonedb");
			connection = ds.getConnection();
			
		}catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
