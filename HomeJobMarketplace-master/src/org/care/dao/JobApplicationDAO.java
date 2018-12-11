package org.care.dao;

import org.care.context.MyApplicationContext;
import org.care.model.JobApplication;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobApplicationDAO implements DAO<JobApplication> {


    @Override
    public void create(JobApplication obj) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "insert into job_application "
                + "(job_id, member_id, expected_pay)"
                + "values(?,?,?)";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            myStmt.setInt(1, obj.getJobId());
            myStmt.setInt(2, obj.getMemberId());
            myStmt.setDouble(3, obj.getExpectedPay());
            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = myStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Job Application creation failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(JobApplicationDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing insert operation: " + e);
        }
    }

    @Override
    public void update(JobApplication obj) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE job_application "
                + "SET expected_pay=? "
                + "WHERE id=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setDouble(1, obj.getExpectedPay());
            myStmt.setInt(2, obj.getId());
            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating job application failed, no rows affected.");
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
        String sql = "UPDATE job_application "
                + "SET status='INACTIVE' "
                + "WHERE id=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, (Integer) id);
            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting job application failed, no rows affected.");
            }

            isDeleted = true;
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing delete operation: " + e);
        }
        return isDeleted;
    }

    @Override
    public JobApplication get(Serializable id) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "select * from job_application where id=?";
        JobApplication jobApplication = null;
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, (Integer) id);

            try (ResultSet myRs = myStmt.executeQuery()) {
                if (myRs.next()) {
                    int jobAppId = myRs.getInt("id");
                    int jobId = myRs.getInt("job_id");
                    int memberId = myRs.getInt("member_id");
                    double expectedPay = myRs.getDouble("expected_pay");
                    JobApplication.Status status = JobApplication.Status.valueOf(myRs.getString("status"));
                    jobApplication = new JobApplication(jobAppId, jobId, memberId, expectedPay, status);
                } else {
                    throw new SQLException("Could not find job with given userId");
                }
            }

        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "jobId " + e);
        }
        return jobApplication;
    }

    public List<JobApplication> get(int userId, int noOfResults) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        List<JobApplication> resultList = new LinkedList<>();
        String sql = "SELECT * FROM job_application WHERE member_id=? LIMIT ?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            myStmt.setInt(2, noOfResults);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    int id = myRs.getInt("id");
                    int jobId = myRs.getInt("job_id");
                    Double expectedPay = myRs.getDouble("expected_pay");
                    JobApplication.Status status = JobApplication.Status.valueOf(myRs.getString("status"));
                    JobApplication tempJobApplication = new JobApplication(id, jobId, userId, expectedPay, status);
                    resultList.add(tempJobApplication);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "member_id and LIMIT: " + e);
        }
        return resultList;
    }

    public List<JobApplication> get(int userId) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        List<JobApplication> resultList = new LinkedList<>();
        String sql = "SELECT * FROM job_application WHERE member_id=?";

        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    int id = myRs.getInt("id");
                    int jobId = myRs.getInt("job_id");
                    double expectedPay = myRs.getDouble("expected_pay");
                    JobApplication.Status status = JobApplication.Status.valueOf(myRs.getString("status"));
                    JobApplication tempJobApplication = new JobApplication(id, jobId, userId, expectedPay, status);
                    resultList.add(tempJobApplication);
                }
            }

        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "member_id: " + e);
        }
        return resultList;
    }

    public List<JobApplication> get(int userId, JobApplication.Status status) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        List<JobApplication> resultList = new LinkedList<>();
        String sql = "SELECT * FROM job_application WHERE member_id=? and status=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {

            myStmt.setInt(1, userId);
            myStmt.setString(2, String.valueOf(status));
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    int id = myRs.getInt("id");
                    int jobId = myRs.getInt("job_id");
                    double expectedPay = myRs.getDouble("expected_pay");
                    JobApplication tempJobApplication = new JobApplication(id, jobId, userId, expectedPay, status);
                    resultList.add(tempJobApplication);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "member_id and status: " + e);
        }
        return resultList;
    }

    public List<Map<String, Object>> getSitterJobsList(int userId) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "select title, start_date, expected_pay from job_application app, job jb " +
                "where app.job_id = jb.id and app.member_id=? ";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    Map<String, Object> tempMap = new HashMap<>();
                    tempMap.put("title", myRs.getString("title"));
                    tempMap.put("startDate", myRs.getTimestamp("start_date"));
                    tempMap.put("expectedPay", myRs.getDouble("expected_pay"));
                    resultList.add(tempMap);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(JobApplicationDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "member_id : " + e);
        }
        return resultList;
    }

    public List<Map<String, Object>> getSitterNAJobsList(int userId) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "SELECT id, title, start_date, pay_per_hour FROM job WHERE id NOT IN " +
                "(SELECT job_id FROM job_application WHERE member_id = ?) AND status='ACTIVE'";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    Map<String, Object> tempMap = new HashMap<>();
                    tempMap.put("jobId", myRs.getInt("id"));
                    tempMap.put("title", myRs.getString("title"));
                    tempMap.put("startDate", myRs.getTimestamp("start_date"));
                    tempMap.put("payPerHour", myRs.getDouble("pay_per_hour"));
                    resultList.add(tempMap);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(JobApplicationDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "member_id : " + e);
        }
        return resultList;
    }

    public List<Map<String, Object>> getAllByUserId(int userId) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "Select a.id, title, expected_pay, pay_per_hour, a.status from job, job_application a where " +
                "job.id = job_id and member_id = ?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    Map<String, Object> tempMap = new HashMap<>();
                    tempMap.put("id", myRs.getInt("id"));
                    tempMap.put("title", myRs.getString("title"));
                    tempMap.put("expectedPay", myRs.getDouble("expected_pay"));
                    tempMap.put("payPerHour", myRs.getDouble("pay_per_hour"));
                    tempMap.put("status", JobApplication.Status.valueOf(myRs.getString("status")));
                    resultList.add(tempMap);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(JobApplicationDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "member_id : " + e);
        }
        return resultList;
    }

    public boolean update(int jobAppId, double expectedPay) {
        boolean isUpdated = false;
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE job_application "
                + "SET expected_pay=? "
                + "WHERE id=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setDouble(1, expectedPay);
            myStmt.setDouble(2, jobAppId);
            int affectedRows = myStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating job application failed, no rows affected.");
            }
            isUpdated = true;
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing update operation: " + e);
        }
        return isUpdated;
    }

    public int deleteByJobId(int jobId) {
        int totalDeletedApps = -1;

        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE job_application "
                + "SET status='INACTIVE' "
                + "WHERE job_id=?";

        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, jobId);
            totalDeletedApps = myStmt.executeUpdate();

        } catch (SQLException e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing delete operation: " + e);
        }
        return totalDeletedApps;
    }

    public List<Map<String, Object>> getAllByJobId(int jobId) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "Select a.id, first_name, a.status, expected_pay from member m, job_application a "
            + "where a.member_id = m.id and job_id = ?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, jobId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    Map<String, Object> tempMap = new HashMap<>();
                    tempMap.put("id", myRs.getInt("id"));
                    tempMap.put("firstName", myRs.getString("first_name"));
                    tempMap.put("status", JobApplication.Status.valueOf(myRs.getString("status")));
                    tempMap.put("expectedPay", myRs.getDouble("expected_pay"));
                    resultList.add(tempMap);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(JobApplicationDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "job_id : " + e);
        }
        return resultList;
    }

    public List<JobApplication> getByJobId(int jobId) {
        Connection myConn = MyApplicationContext.getJdbcConnection();
        List<JobApplication> resultList = new LinkedList<>();
        String sql = "SELECT * FROM job_application WHERE job_id=?";

        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, jobId);
            try (ResultSet myRs = myStmt.executeQuery()) {
                while (myRs.next()) {
                    int id = myRs.getInt("id");
                    int userId = myRs.getInt("member_id");
                    double expectedPay = myRs.getDouble("expected_pay");
                    JobApplication.Status status = JobApplication.Status.valueOf(myRs.getString("status"));
                    JobApplication tempJobApplication = new JobApplication(id, jobId, userId, expectedPay, status);
                    resultList.add(tempJobApplication);
                }
            }

        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "job_id: " + e);
        }
        return resultList;
    }

    public int deleteByUserId(int userId) {
        int totalDeletedApps = -1;

        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE job_application "
                + "SET status='INACTIVE' "
                + "WHERE member_id=?";

        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, userId);
            totalDeletedApps = myStmt.executeUpdate();

        } catch (SQLException e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing delete operation: " + e);
        }
        return totalDeletedApps;
    }
}
