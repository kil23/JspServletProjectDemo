package com.myapp.dao.daolayer;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.dao.model.Seeker;
import com.myapp.util.ConnectionUtil;

public class SeekerDao implements SeekerDaoInterf {

	public Seeker getSeeker(int id) {
		Connection conn = null;
		Statement stmt;
		try {
			if(conn == null || conn.isClosed()) {
				conn = ConnectionUtil.getConnection();
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM seeker WHERE memberid="+id);
			if(rs.next()) {
				Seeker sk = new Seeker();
				sk.setTotalChildren(rs.getInt("totalchildren"));
				sk.setSpouse(rs.getString("spousename"));
				sk.setMemberId(rs.getInt("memberid"));
				return sk;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
		      if (conn != null) 
		        try {conn.close();} catch (SQLException e) {}
		      }
		return null;
	}

	public int insertSeeker(Seeker sk) {
		Connection conn = null;
		
		try {
			if(conn == null || conn.isClosed()) {
				conn = ConnectionUtil.getConnection();
			}
			PreparedStatement ps = conn.prepareStatement("INSERT INTO seeker (totalchildren,spousename) "
					+ "VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(8, sk.getTotalChildren());
			ps.setString(9, sk.getSpouseName());
			
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

	public boolean updateSeeker(Seeker sk) {
		Connection conn = null;
		
		try {
			if(conn == null || conn.isClosed()) {
				conn = ConnectionUtil.getConnection();
			}
			PreparedStatement ps = conn.prepareStatement("UPDATE seeker SET totalchildren=?, spousename=? WHERE memberid=?");
			ps.setInt(1, sk.getTotalChildren());
			ps.setString(2, sk.getSpouseName());
			ps.setInt(4, sk.getMemberId());
			
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
