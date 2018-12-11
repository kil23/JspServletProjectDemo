package com.myapp.dao.daolayer;

import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.dao.model.JobApplication;
import com.myapp.dao.model.JobApplication.Status;
import com.myapp.util.ConnectionUtil;

public class JobAppDao implements JobAppDaoInterf {

	public JobApplication getJobAppUsingAppId(int jobAppId) {
		Connection conn = ConnectionUtil.getConnection();
		Statement stmt;
		JobApplication jobApp = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM jobapplication WHERE id="+jobAppId);
			if(rs.next()) {
				jobApp = new JobApplication();
				jobApp.setJobApplicationId(rs.getInt("id"));
				jobApp.setJobid(rs.getInt("jobid"));
				jobApp.setMemberid(rs.getInt("memid"));
				jobApp.setExpectedPay(rs.getDouble("expectedpay"));
			}else {
                throw new SQLException("Job not found.");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobApp;
	}
	
	
	public List<JobApplication> getAppByUserIdNStatus(int userId, JobApplication.Status status) {
		Connection conn = ConnectionUtil.getConnection();
        List<JobApplication> resultList = new LinkedList<>();
        JobApplication jobApplication = null;
        String sql = "SELECT * FROM job_application WHERE memid=? and status=?";
        try (PreparedStatement myStmt = conn.prepareStatement(sql)) {

            myStmt.setInt(1, userId);
            myStmt.setString(2, String.valueOf(status));
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                	jobApplication.setJobApplicationId(myRs.getInt("id"));
                	jobApplication.setJobid(myRs.getInt("jobid"));
                	jobApplication.setExpectedPay(myRs.getDouble("expectedpay"));
                    
                    resultList.add(jobApplication);
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return resultList;
    }
	
	public List<JobApplication> getAppUsingUidNnumResults(int userId, int numResults) {
		Connection conn = ConnectionUtil.getConnection();
        List<JobApplication> resultList = new LinkedList<>();
        String sql = "SELECT * FROM job_application WHERE memid=? LIMIT ?";
        JobApplication jobApplication = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setInt(1, userId);
        	stmt.setInt(2, numResults);
            try (ResultSet myRs = stmt.executeQuery()) {
                while (myRs.next()) {
                	jobApplication.setJobApplicationId(myRs.getInt("id"));
                    jobApplication.setJobid(myRs.getInt("jobid"));
                    jobApplication.setExpectedPay(myRs.getDouble("expectedpay"));
                    jobApplication.setStatus(Status.valueOf(myRs.getString("status")));
                    resultList.add(jobApplication);
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return resultList;
    }
	
	public List<JobApplication> getUsingUserid(int userId) {
        Connection conn = ConnectionUtil.getConnection();
        List<JobApplication> resultList = new LinkedList<>();
        JobApplication jobApplication = null;
        String sql = "SELECT * FROM job_application WHERE memid=?";

        try (PreparedStatement myStmt = conn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                	jobApplication.setJobApplicationId(myRs.getInt("id"));
                    jobApplication.setJobid(myRs.getInt("jobid"));
                    jobApplication.setExpectedPay(myRs.getDouble("expectedpay"));
                    jobApplication.setStatus(Status.valueOf(myRs.getString("status")));
                    resultList.add(jobApplication);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
	
	public List<JobApplication> getUsingUseridNStatus(int userId, JobApplication.Status status) {
        Connection conn = ConnectionUtil.getConnection();
        List<JobApplication> resultList = new LinkedList<>();
        JobApplication jobApplication = null;
        String sql = "SELECT * FROM job_application WHERE member_id=? and status=?";
        try (PreparedStatement myStmt = conn.prepareStatement(sql)) {

            myStmt.setInt(1, userId);
            myStmt.setString(2, String.valueOf(status));
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                	jobApplication.setJobApplicationId(myRs.getInt("id"));
                    jobApplication.setJobid(myRs.getInt("jobid"));
                    jobApplication.setExpectedPay(myRs.getDouble("expectedpay"));
                    resultList.add(jobApplication);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
	
	public boolean insertUsingJobApp(JobApplication jobApp) {
		Connection conn = ConnectionUtil.getConnection();
		try {
			 PreparedStatement ps = conn.prepareStatement("INSERT INTO jobapplication (jobid, memid, expectedpay) "
			 		+ "VALUES ( ?, ?, ?, ?)");
			 ps.setInt(1, jobApp.getJobid());
			 ps.setInt(2, jobApp.getMemberid());
			 ps.setDouble(3, jobApp.getExpectedPay());
			 int affectedRows = ps.executeUpdate();
			
			 if (affectedRows == 0) {
	                throw new SQLException("Creating user failed, no rows affected.");
	         }
			 
			 try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	             if (generatedKeys.next()) {
	            	 jobApp.setJobApplicationId(generatedKeys.getInt(1));
	             } else {
	                 throw new SQLException("Job Application creation failed, no ID obtained.");
	             }
	         }
			 
			 if(affectedRows==1) {
				 return true;
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUsingJobApp(JobApplication jobApp) {
		Connection conn = ConnectionUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE jobapplication SET expectpay=? WHERE id=?");
			ps.setInt(1, jobApp.getJobid());
			ps.setInt(2, jobApp.getMemberid());
			ps.setDouble(3, jobApp.getExpectedPay());
			ps.setString(4, jobApp.getStatus().toString());
			
			int affectedRows = ps.executeUpdate();
			
			if (affectedRows == 0) {
	                throw new SQLException("Updating job application failed, no rows affected.");
	        }
			
			 if(affectedRows==1) {
				 return true;
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUsingJobAppId(int jobAppId) {
		Connection conn = ConnectionUtil.getConnection();
		 boolean isDeleted = false;
		 String sql = "UPDATE jobApplication SET status='Inactive' WHERE id=?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, jobAppId);
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows == 0) {
                throw new SQLException("Deleting job application failed, no rows affected.");
            }
			isDeleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDeleted;
	}
	
	public int deleteUsingUserId(int userId) {
		
		Connection conn = ConnectionUtil.getConnection();
		int rowsDeleted = -1;
		String sql = "Update jobApplication SET status='INACTIVE' WHERE memid=?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, userId);
			rowsDeleted = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}
}
