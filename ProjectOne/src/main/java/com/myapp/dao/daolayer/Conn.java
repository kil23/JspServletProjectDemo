package com.myapp.dao.daolayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	
	public static final String URL = "jdbc:mysql://localhost/projectonedb";
	public static final String USER = "root";
	public static final String PASS = "root123";
	
	public static Connection getConnection() {
	    try {
	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
	        return DriverManager.getConnection(URL, USER, PASS);
	    } catch (SQLException ex) {
	        throw new RuntimeException("Error connecting to the database", ex);
	    }
	}
}
