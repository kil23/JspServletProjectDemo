package com.myapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.myapp.dao.model.Member;
import com.myapp.service.MemberService;

public class ContextConnectionUtil {
	
	public static ThreadLocal<ContextConnectionUtil> threadLocal = new ThreadLocal<>();
	private HttpServletRequest request;
	private static Connection connection;
	
	public static void create(HttpServletRequest httpServletRequest) {
		if(threadLocal != null) {
			throw new IllegalStateException("Threadlocal application context already present.");
		}
		ContextConnectionUtil connectionUtil = new ContextConnectionUtil();
		threadLocal.set(connectionUtil);
		connectionUtil.request = httpServletRequest;
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
        HttpSession session =  request.getSession(false);
        if(session != null) {
        	Integer memberid = (Integer) session.getAttribute("id");
        	 if (memberid != null) {
                 return MemberService.getMemberById(memberid);
             }
        }
        return null;
    }
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectonedb"
                    ,"root","root123");	
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
