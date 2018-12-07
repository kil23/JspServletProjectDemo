package com.myapp.dao.daolayer;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.dao.model.JobApplication;

public class JobAppDao implements JobAppDaoInterf {

	public JobApplication getJobApp(int jobAppId) {
		Connection conn = Conn.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM jobapplication WHERE id="+jobAppId);
			if(rs.next()) {
				JobApplication j = new JobApplication();
				j.setJobid(rs.getInt("jobid"));
				j.setMemberid(rs.getInt("memid"));
				j.setExpectedPay(rs.getInt("expectpay"));
				return j;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertJobApp(JobApplication j) {
		Connection conn = Conn.getConnection();
		try {
			 PreparedStatement ps = conn.prepareStatement("INSERT INTO jobapplication ('jobid', 'memid', 'expectpay', 'status') "
			 		+ "VALUES ( ?, ?, ?, ?)");
			 ps.setInt(1, j.getJobid());
			 ps.setInt(2, j.getMemberid());
			 ps.setInt(3, j.getExpectedPay());
			 ps.setString(4, j.getStatus().toString());
			 int i = ps.executeUpdate();
			 if(i==1) {
				 return true;
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateJobApp(JobApplication j) {
		Connection conn = Conn.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE jobapplication SET jobid=?, memid=?, expectpay=?, "
					+ "status=? WHERE id=?");
			ps.setInt(1, j.getJobid());
			ps.setInt(2, j.getMemberid());
			ps.setInt(3, j.getExpectedPay());
			ps.setString(4, j.getStatus().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteJobApp(int jobAppId) {
		Connection conn = Conn.getConnection();
		try {
			Statement stmt = conn.createStatement();
			int i = stmt.executeUpdate("DELETE FROM jobapplication WHERE id="+jobAppId);
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
