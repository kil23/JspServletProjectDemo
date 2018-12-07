package com.myapp.dao.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.myapp.dao.model.Member.Status;
import com.myapp.dao.model.Member.Type;
import com.myapp.dao.model.Sitter;

public class SitterDao implements SitterDaoInterf {

	public Sitter getSitter(int id) {
		Connection con = Conn.getConnection();
		
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sitter WHERE memberid="+id);
			if(rs.next()) {
				Sitter st = new Sitter();
				st.setFname(rs.getString("fname"));
				st.setLname(rs.getString("lname"));
				st.setPhone(rs.getInt("phone"));
				st.setEmail(rs.getString("email"));
				st.setType(Type.valueOf(rs.getString("type")));
				st.setAddr(rs.getString("address"));
				st.setpassword(rs.getString("password"));
				st.setYrExp(rs.getString("yrexp"));
				st.setEpay(rs.getString("expectedpay"));
				st.setStatus(Status.valueOf("status"));
				return st;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertSitter(Sitter st) {
		Connection con = Conn.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO sitter ('fname','lname','phone','email','type','address',"
					+ "'password','yrexp','expectedpay','status') VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, st.getFname());
			ps.setString(2, st.getLname());
			ps.setInt(3, st.getPhone());
			ps.setString(4, st.getEmail());
			ps.setString(5, st.getType().toString());
			ps.setString(6, st.getAddr());
			ps.setString(7, st.getpassword());
			ps.setString(8, st.getYrExp());
			ps.setString(9, st.getEpay());
			ps.setString(10, st.getStatus().toString());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSitter(Sitter st) {
		Connection con = Conn.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE sitter SET fname=?, lname=?, phone=?, email=?, type=?, address=?, "
					+ "password=?, yrexp=?, expectedpay=?, status=? WHERE memberid=?");
			ps.setString(1, st.getFname());
			ps.setString(2, st.getLname());
			ps.setInt(3, st.getPhone());
			ps.setString(4, st.getEmail());
			ps.setString(5, st.getType().toString());
			ps.setString(6, st.getAddr());
			ps.setString(7, st.getpassword());
			ps.setString(8, st.getYrExp());
			ps.setString(9, st.getEpay());
			ps.setString(10, st.getStatus().toString());
			
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSitter(int id) {
		Connection con = Conn.getConnection();
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("DELETE FROM sitter WHERE memberid="+id);
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
