package com.myapp.dao.daolayer;

import java.sql.Statement;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.dao.model.Member.Status;
import com.myapp.dao.model.Seeker;

public class SeekerDao implements SeekerDaoInterf {

	public Seeker getSeeker(int id) {
		Connection conn = Conn.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM seeker WHERE memberid="+id);
			if(rs.next()) {
				Seeker sk = new Seeker();
				sk.setFname(rs.getString("fname"));
				sk.setLname(rs.getString("lname"));
				sk.setPhone(rs.getInt("phone"));
				sk.setEmail(rs.getString("email"));
				sk.setAddr(rs.getString("addr"));
				sk.setStatus(Status.valueOf(rs.getString("status")));
				sk.setMemberId(rs.getInt("memberid"));
				return sk;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	public boolean insertSeeker(Seeker sk) {
		Connection con = Conn.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO seeker ('fname','lname','phone','email','type','address',"
					+ "'password','numchild','spouse',status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, sk.getFname());
			ps.setString(2, sk.getLname());
			ps.setInt(3, sk.getPhone());
			ps.setString(4, sk.getEmail());
			ps.setString(5, sk.getType().toString());
			ps.setString(6, sk.getAddr());
			ps.setString(7, sk.getpassword());
			ps.setString(8, sk.getNoOfChildren());
			ps.setString(9, sk.getSpouse());
			ps.setString(10, sk.getStatus().toString());
			
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	public boolean updateSeeker(Seeker sk) {
		Connection con = Conn.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE seeker SET fname=?, lname=?, phone=?, email=?, type=?, "
					+ "address=?, password=?, numchild=?, spouse=?, status=? WHERE memberid=?");
			ps.setString(1, sk.getFname());
			ps.setString(2, sk.getLname());
			ps.setInt(3, sk.getPhone());
			ps.setString(4, sk.getEmail());
			ps.setString(5, sk.getType().toString());
			ps.setString(6, sk.getAddr());
			ps.setString(7, sk.getpassword());
			ps.setString(8, sk.getNoOfChildren());
			ps.setString(9, sk.getSpouse());
			ps.setString(10, sk.getStatus().toString());
			ps.setInt(11, sk.getMemberId());
			
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSeeker(int id) {
		
		Connection con = Conn.getConnection();
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("DELETE FROM seeker WHERE memberid="+id);
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
