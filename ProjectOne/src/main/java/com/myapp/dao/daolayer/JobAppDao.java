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
import com.myapp.util.ContextConnectionUtil;

public class JobAppDao implements JobAppDaoInterf {

	public JobApplication getJobAppByJobAppId(int jobAppid) {
		Connection conn = ContextConnectionUtil.getConnection();
		JobApplication jobApp = null;
		String sql = "Select * from jobapplication Where id=?";
		try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
			preparedStatement.setInt(1, jobAppid);
			try(ResultSet rs = preparedStatement.executeQuery()){
				jobApp = new JobApplication();
				jobApp.setJobApplicationId(jobAppid);
				jobApp.setJobid(rs.getInt("jobid"));
				jobApp.setMemberid(rs.getInt("memid"));
				jobApp.setExpectedPay(rs.getDouble("expectedpay"));
				jobApp.setStatus(Status.valueOf(rs.getString("status")));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobApp;
	}
	
	public List<JobApplication> getJobAppListByJobId(int jobId) {
		Connection conn = ContextConnectionUtil.getConnection();
		Statement stmt;
		List<JobApplication> resultList = new LinkedList<>();
		JobApplication jobApp = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM jobapplication WHERE id='"+jobId+"'");
			if(rs.next()) {
				jobApp = new JobApplication();
				jobApp.setJobApplicationId(rs.getInt("id"));
				jobApp.setJobid(jobId);
				jobApp.setMemberid(rs.getInt("memid"));
				jobApp.setExpectedPay(rs.getDouble("expectedpay"));
				jobApp.setStatus(Status.valueOf(rs.getString("status")));
				resultList.add(jobApp);
				return resultList;
			}else {
                throw new SQLException("Job not found.");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) 
		        try {
		        		conn.close();
		        }catch (SQLException e) {}
		      }
		return null;
		
	}
	
	public List<JobApplication> getJobAppListByUserIdNStatus(int userId, JobApplication.Status status) {
		Connection conn = ContextConnectionUtil.getConnection();
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
	
	public List<JobApplication> getJobAppListUsingUidNnumResults(int userId, int numResults) {
		Connection conn = ContextConnectionUtil.getConnection();
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
	
	public List<JobApplication> getJobAppListUsingUserid(int userId) {
        Connection conn = ContextConnectionUtil.getConnection();
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
	
	public List<JobApplication> getJobAppListUsingUseridNStatus(int userId, JobApplication.Status status) {
        Connection conn = ContextConnectionUtil.getConnection();
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
		Connection conn = ContextConnectionUtil.getConnection();
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
		Connection conn = ContextConnectionUtil.getConnection();
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

	public boolean deleteJobAppUsingJobAppId(int jobAppid) {
		Connection conn = ContextConnectionUtil.getConnection();
		 
		 String sql = "UPDATE jobApplication SET status='Inactive' WHERE id=?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, jobAppid);
			int affectedRows = stmt.executeUpdate();
			if(affectedRows == 1) {
				return true;
			}
			if (affectedRows == 0) {
               throw new SQLException("Deleting job application failed, no rows affected.");
           }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int deleteUsingJobId(int jobId) {
		Connection conn = ContextConnectionUtil.getConnection();
		 
		 String sql = "UPDATE jobApplication SET status='Inactive' WHERE jobid=?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, jobId);
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows == 0) {
                throw new SQLException("Deleting job application failed, no rows affected.");
            }
			
			return affectedRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteUsingUserId(int userId) {
		
		Connection conn = ContextConnectionUtil.getConnection();
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
