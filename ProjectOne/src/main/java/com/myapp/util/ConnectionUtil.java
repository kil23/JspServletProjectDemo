package com.myapp.util;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionUtil {
	
//	private static final String URL = "jdbc:mysql://localhost/projectonedb";
//	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//	private static final String USER = "root";
//	private static final String PASS = "root123";
	
	//	static {
//		try {
//			Class.forName(DRIVER_NAME);
//			connection =  DriverManager.getConnection(URL, USER, PASS);
//		} catch (SQLException | ClassNotFoundException ex) {
//			throw new RuntimeException("Error connecting to the database", ex);
//		}
//	}
	
	public static Connection getConnection() {
		Connection connection = null;
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
	
//	public static void dbCleanUp(Connection conn, Statement st, ResultSet rs) {
//		if(conn!=null) {
//			try {
//				conn.close();
//			}catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
