package com.myapp.dao.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.myapp.dao.model.Member.Status;
import com.myapp.dao.model.Sitter;
import com.myapp.util.ContextConnectionUtil;

public class SitterDao extends MemberDao implements SitterDaoInterf {

	public Sitter getSitter(int id) {
		Connection conn = null;
		
		Statement stmt;
		try {
			if(conn == null || conn.isClosed()) {
				conn = ContextConnectionUtil.getConnection();
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sitter WHERE memberid='"+id+"'");
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

	public boolean insertSitter(Sitter st) {
		Connection conn = null;
		try {
			if(conn == null || conn.isClosed()) {
				conn = ContextConnectionUtil.getConnection();
			}
			PreparedStatement ps = conn.prepareStatement("INSERT INTO sitter (memberid,yrexp,expectedpay) "
					+ "VALUES (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, st.getMemberId());
			ps.setInt(2, st.getYrExp());
			ps.setInt(3, st.getEpay());
			
			int affectedRow = ps.executeUpdate();
			if(affectedRow == 1) {
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

	public boolean updateSitter(Sitter st) {
		Connection conn = null;
		
		try {
			if(conn == null || conn.isClosed()) {
				conn = ContextConnectionUtil.getConnection();
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

	@Override
	public boolean deleteSitter(int id) {
		return super.deleteMember(id);
	}

}
