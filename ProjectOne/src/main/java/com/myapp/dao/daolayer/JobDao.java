package com.myapp.dao.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.myapp.dao.model.Job;
import com.myapp.dao.model.JobApplication.Status;
import com.myapp.util.ConnectionUtil;

public class JobDao implements JobDaoInterf{

	public boolean insertJob(Job job) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "insert into job (title, postedby, startdate, enddate, payperhr) Values ( ?, ?, ?, ?, ?)";
		try {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, job.getTitle());
				ps.setInt(2, job.getPostedBy());
				ps.setTimestamp(3, job.getStartDate());
				ps.setTimestamp(4, job.getEndDate());
				ps.setDouble(5, job.getPayPerHr());
				
				int affectedRows = ps.executeUpdate();
				
				if(affectedRows==0) {
					throw new SQLException("Job insertion failed, no rows affected.");
				}
				
				try (ResultSet generatedkeys = ps.getGeneratedKeys()){
					if(generatedkeys.next()) {
						job.setJobid(generatedkeys.getInt(1));
					}else {
						throw new SQLException("Job insertion failed, no id generated.");
					}
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateJob(Job job) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE job SET title=?, startdate=?, enddate=?, payperhr=? WHERE jobid=?";
        try (PreparedStatement myStmt = conn.prepareStatement(sql)) {
            myStmt.setString(1, job.getTitle());
            myStmt.setTimestamp(2, job.getStartDate());
            myStmt.setTimestamp(3, job.getEndDate());
            myStmt.setDouble(4, job.getPayPerHr());
            myStmt.setInt(5, job.getJobid());
            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating job failed, no rows affected.");
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
		return false;
	}

	public boolean deleteJob(int jobid) {
		Connection conn = ConnectionUtil.getConnection();
		boolean isDeleted = false;
 
	    String sql = "UPDATE job SET status='Inactive' WHERE jobid=?";
	    
	    try (PreparedStatement myStmt = conn.prepareStatement(sql)) {
	          myStmt.setInt(1, (Integer) jobid);
	          int affectedRows = myStmt.executeUpdate();

	          if (affectedRows == 0) {
	              throw new SQLException("Deleting job failed, no rows affected.");
	          }

	          isDeleted = true;
	       } catch (SQLException e) {
	           e.printStackTrace();
	    }
	    return isDeleted;
	}
	
	public Job getJob(int jobAppId) {
		Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from job where jobid=?";
        Job job = null;
        try (PreparedStatement myStmt = conn.prepareStatement(sql)) {
            myStmt.setInt(1, jobAppId);

            try (ResultSet myRs = myStmt.executeQuery()) {
                if (myRs.next()) {
                    job = new Job();
                	job.setJobid(myRs.getInt("jobid"));
                    job.setTitle(myRs.getString("title"));
                    job.setPostedBy(myRs.getInt("postedby"));
                    job.setStartDate(myRs.getTimestamp("startdate"));
                    job.setEndDate(myRs.getTimestamp("end_date"));
                    job.setPayPerHr(myRs.getDouble("payperhr"));
                    job.setStatus(Status.valueOf(myRs.getString("status")));
                } else {
                    throw new SQLException("Job not found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
	}
	
	public List<Job> getUserJobs(int uid){
		Connection conn = ConnectionUtil.getConnection();
		List<Job> resultList = new LinkedList<>();

        String sql = "SELECT * FROM job WHERE postedby=?";
        try (PreparedStatement myStmt = conn.prepareStatement(sql)) {
            myStmt.setInt(1, uid);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                	Job job = new Job();
                	job.setJobid(myRs.getInt("jobid"));
                    job.setTitle(myRs.getString("title"));
                    job.setPostedBy(myRs.getInt("postedby"));
                    job.setStartDate(myRs.getTimestamp("startdate"));
                    job.setEndDate(myRs.getTimestamp("enddate"));
                    job.setPayPerHr(myRs.getDouble("payperhr"));
                    job.setStatus(Status.valueOf(myRs.getString("status")));
                    resultList.add(job);
                }
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return resultList;
	}

	@Override
	public List<Job> getUserJob(int uid) {
		Connection conn = ConnectionUtil.getConnection();
		List<Job> resultList = new LinkedList<>();

        String sql = "SELECT * FROM job WHERE postedby=?";
        try (PreparedStatement myStmt = conn.prepareStatement(sql)) {
            myStmt.setInt(1, uid);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                	Job job = new Job();
                	job.setJobid(myRs.getInt("jobid"));
                    job.setTitle(myRs.getString("title"));
                    job.setPostedBy(myRs.getInt("posted_by"));
                    job.setStartDate(myRs.getTimestamp("start_date"));
                    job.setEndDate(myRs.getTimestamp("end_date"));
                    job.setPayPerHr(myRs.getDouble("payperhr"));
                    job.setStatus(Status.valueOf(myRs.getString("status")));
                    resultList.add(job);
                }
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return resultList;
	}

}
