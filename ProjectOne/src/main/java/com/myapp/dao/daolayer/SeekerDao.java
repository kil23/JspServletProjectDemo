package com.myapp.dao.daolayer;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.dao.model.Seeker;
import com.myapp.util.ContextConnectionUtil;

public class SeekerDao extends MemberDao implements SeekerDaoInterf {

	public Seeker getSeeker(int id) {
		Connection conn = null;
		Statement stmt;
		try {
			if(conn == null || conn.isClosed()) {
				conn = ContextConnectionUtil.getConnection();
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM seeker WHERE memberid='"+id+"'");
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

	public boolean insertSeeker(Seeker sk) {
		Connection conn = null;
		
		try {
			if(conn == null || conn.isClosed()) {
				conn = ContextConnectionUtil.getConnection();
			}
			PreparedStatement ps = conn.prepareStatement("INSERT INTO seeker (memberid,totalchildren,spousename) "
					+ "VALUES (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, sk.getId());
			ps.setInt(2, sk.getTotalChildren());
			ps.setString(3, sk.getSpouseName());
			
			int affectedRow = ps.executeUpdate();
			if(affectedRow==1) {
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

	public boolean updateSeeker(Seeker sk) {
		Connection conn = null;
		
		try {
			if(conn == null || conn.isClosed()) {
				conn = ContextConnectionUtil.getConnection();
			}
			PreparedStatement ps = conn.prepareStatement("UPDATE seeker SET totalchildren=?, spousename=? WHERE memberid=?");
			ps.setInt(1, sk.getTotalChildren());
			ps.setString(2, sk.getSpouseName());
			ps.setInt(3, sk.getMemberId());
			
			int affectedRow = ps.executeUpdate();
			if(affectedRow==1) {
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

	@Override
	public boolean deleteSeeker(int id) {
		return super.deleteMember(id);
	}
}
