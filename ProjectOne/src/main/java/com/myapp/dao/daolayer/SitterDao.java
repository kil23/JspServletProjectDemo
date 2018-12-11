package com.myapp.dao.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.myapp.dao.model.Member.Status;
import com.myapp.dao.model.Sitter;
import com.myapp.util.ConnectionUtil;

public class SitterDao implements SitterDaoInterf {

	public Sitter getSitter(int id) {
		Connection conn = null;
		
		Statement stmt;
		try {
			if(conn == null || conn.isClosed()) {
				conn = ConnectionUtil.getConnection();
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sitter WHERE memberid="+id);
			if(rs.next()) {
				Sitter st = new Sitter();
				st.setYrExp(rs.getInt("yrexp"));
				st.setEpay(rs.getInt("expectedpay"));
				st.setMemberId(rs.getInt("memberId"));
				return st;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		      if (conn != null) 
			        try {conn.close();} catch (SQLException e) {}
			      }
		return null;
	}

	public int insertSitter(Sitter st) {
		Connection conn = null;
		try {
			if(conn == null || conn.isClosed()) {
				conn = ConnectionUtil.getConnection();
			}
			PreparedStatement ps = conn.prepareStatement("INSERT INTO sitter (yrexp,expectedpay) "
					+ "VALUES ( ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, st.getYrExp());
			ps.setInt(2, st.getEpay());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				 ResultSet rs = ps.getGeneratedKeys();
				    rs.next();
				   return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		      if (conn != null) 
			        try {conn.close();} catch (SQLException e) {}
			      }
		return 0;
	}

	public boolean updateSitter(Sitter st) {
		Connection conn = null;
		
		try {
			if(conn == null || conn.isClosed()) {
				conn = ConnectionUtil.getConnection();
			}
			PreparedStatement ps = conn.prepareStatement("UPDATE sitter SET yrexp=?, expectedpay=? WHERE memberid=?");
			ps.setInt(1, st.getYrExp());
			ps.setInt(2, st.getEpay());
			ps.setInt(3, st.getMemberId());
			
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		      if (conn != null) 
			        try {conn.close();} catch (SQLException e) {}
			      }
		return false;
	}

}
