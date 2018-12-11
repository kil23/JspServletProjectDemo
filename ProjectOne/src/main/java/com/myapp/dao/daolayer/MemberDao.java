package com.myapp.dao.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.myapp.dao.model.Member;
import com.myapp.dao.model.Member.Status;
import com.myapp.dao.model.Member.Type;
import com.myapp.util.ConnectionUtil;

public class MemberDao implements MemberDaoInterf{

	@Override
	public Member getMember(int id) {
		Connection conn = ConnectionUtil.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from member Where id="+id);
			if(rs.next()) {
				Member mem = new Member();
				mem.setFname(rs.getString("fname"));
				mem.setLname(rs.getString("lname"));
				mem.setPhone(rs.getInt("phone"));
				mem.setEmail(rs.getString("email"));
				mem.setType(Type.valueOf(rs.getString("type")));
				mem.setAddr(rs.getString("address"));
				mem.setpassword(rs.getString("password"));
				mem.setStatus(Status.valueOf(rs.getString("status")));
				return mem;
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
	
	@Override
	public boolean checkEmail(String email) {
		
		Connection conn = ConnectionUtil.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM member WHERE email='"+email+"'");
			if(rs.first()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
		      if (conn != null) 
		        try {conn.close();} catch (SQLException e) {}
		      }
		return false;
	}

	@Override
	public Member checkUserCredential(String email, String password) {
		Connection conn = ConnectionUtil.getConnection();
		Member mem = new Member();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM member WHERE email="+email+" AND password="+password);
			if(rs.next()) {
				mem.setId(rs.getInt("id"));
				mem.setFname(rs.getString("fname"));
				mem.setLname(rs.getString("lname"));
				mem.setPhone(rs.getInt("phone"));
				mem.setEmail(rs.getString("email"));
				mem.setType(Type.valueOf(rs.getString("type")));
				mem.setAddr(rs.getString("address"));
				mem.setpassword(rs.getString("password"));
				mem.setStatus(Status.valueOf("status"));
			}
			if(rs.first()) {
				return mem;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		      if (conn != null) 
			        try {conn.close();} catch (SQLException e) {}
			      }
		return null;
	}

	@Override
	public int insertMember(Member mem) {
		Connection conn = ConnectionUtil.getConnection();
		try {
			String sql = "INSERT INTO member (fname,lname,phone,email,type,"
					+ "address,password,status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, mem.getFname());
			ps.setString(2, mem.getLname());
			ps.setInt(3, mem.getPhone());
			ps.setString(4, mem.getEmail());
			ps.setString(5, mem.getType().toString());
			ps.setString(6, mem.getAddr());
			ps.setString(7, mem.getpassword());
			ps.setString(8, mem.getStatus().toString());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				   ResultSet rs = ps.getGeneratedKeys();
				    rs.next();
				   return rs.getInt(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
		      if (conn != null) 
		        try {conn.close();} catch (SQLException e) {}
		      }
		return 0;
	}

	@Override
	public boolean updateMember(Member mem) {
		Connection conn = ConnectionUtil.getConnection();
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("UPDATE member SET fname=?, lname=? phone=? email=? type=? address=? password=? "
					+ "status=? WHERE id=?");
			ps.setString(1, mem.getFname());
			ps.setString(2, mem.getLname());
			ps.setInt(3, mem.getPhone());
			ps.setString(4, mem.getEmail());
			ps.setString(5, mem.getType().toString());
			ps.setString(6, mem.getAddr());
			ps.setString(8, mem.getpassword());
			ps.setString(9, mem.getStatus().toString());
			ps.setInt(10, mem.getId());
			
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
		      if (conn != null) 
		        try {conn.close();} catch (SQLException e) {}
		      }
		return false;
	}

//	@Override
//	public boolean deleteMember(int id) {
//		Connection conn = ConnectionUtil.getConnection();
//		try {
//			Statement stmt = conn.createStatement();
//			int i = stmt.executeUpdate("DELETE FROM member WHERE id="+id);
//			if(i==1) {
//				return true;
//			}
//		} catch (SQLException e) {	
//			e.printStackTrace();
//		}finally {
//		      if (conn != null) 
//			        try {conn.close();} catch (SQLException e) {}
//			      }
//		return false;
//	}

}
