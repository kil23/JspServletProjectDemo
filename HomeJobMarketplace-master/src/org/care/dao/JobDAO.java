package org.care.dao;

import org.care.context.MyApplicationContext;
import org.care.model.Job;

import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobDAO implements DAO<Job> {

    @Override
    public void create(Job obj) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "insert into job "
                + "(title, posted_by, start_date, end_date, pay_per_hour)"
                + "values(?,?,?,?,?)";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            myStmt.setString(1, obj.getTitle());
            myStmt.setInt(2, obj.getPostedBy());
            myStmt.setTimestamp(3, obj.getStartDate());
            myStmt.setTimestamp(4, obj.getEndDate());
            myStmt.setDouble(5, obj.getPayPerHour());

            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Job creation failed, no rows affected.");
            }

            try (ResultSet generatedKeys = myStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Job creation failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing insert operation: " + e);
        }
    }

    @Override
    public void update(Job obj) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE job "
                + "SET title=?, start_date=?, end_date=?, pay_per_hour=? "
                + "WHERE id=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setString(1, obj.getTitle());
            myStmt.setTimestamp(2, obj.getStartDate());
            myStmt.setTimestamp(3, obj.getEndDate());
            myStmt.setDouble(4, obj.getPayPerHour());
            myStmt.setInt(5, obj.getId());
            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating job failed, no rows affected.");
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing update operation: " + e);
        }
    }

    @Override
    public boolean delete(Serializable id) {
        boolean isDeleted = false;

        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE job "
                + "SET status='INACTIVE' "
                + "WHERE id=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, (Integer) id);
            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting job failed, no rows affected.");
            }

            isDeleted = true;
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing delete operation: " + e);
        }
        return isDeleted;
    }

    @Override
    public Job get(Serializable id) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "select * from job where id=?";
        Job job = null;
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, (Integer) id);

            try (ResultSet myRs = myStmt.executeQuery()) {
                if (myRs.next()) {
                    int jobId = myRs.getInt("id");
                    String title = myRs.getString("title");
                    int memberId = myRs.getInt("posted_by");
                    Timestamp startDate = myRs.getTimestamp("start_date");
                    Timestamp endDate = myRs.getTimestamp("end_date");
                    double payPerHour = myRs.getDouble("pay_per_hour");
                    Job.Status status = Job.Status.valueOf(myRs.getString("status"));
                    job = new Job(jobId, title, memberId, startDate, endDate, payPerHour, status);
                } else {
                    throw new SQLException("Could not find job with given userId");
                }
            }

        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "jobId " + e);
        }
        return job;
    }

    public List<Job> getJobsPostedBy(int userId) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        List<Job> resultList = new LinkedList<>();

        String sql = "SELECT * FROM job WHERE posted_by=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    int id = myRs.getInt("id");
                    String title = myRs.getString("title");
                    Timestamp startDate = myRs.getTimestamp("start_date");
                    Timestamp endDate = myRs.getTimestamp("end_date");
                    double payPerHour = myRs.getDouble("pay_per_hour");
                    Job.Status status = Job.Status.valueOf(myRs.getString("status"));
                    Job tempJob = new Job(id, title, userId, startDate, endDate, payPerHour, status);
                    resultList.add(tempJob);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(JobDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "posted_by: " + e);
        }
        return resultList;
    }
}
