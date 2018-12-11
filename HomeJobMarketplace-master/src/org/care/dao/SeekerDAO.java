package org.care.dao;

import org.care.context.MyApplicationContext;
import org.care.model.Member;
import org.care.model.Seeker;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerDAO extends MemberDAO<Seeker> {

    @Override
    public void create(Seeker obj) {
        //inserting data into member first
        super.create(obj);
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "insert into seeker "
                + "(seeker_id, total_children, spouse_name)"
                + "values(?,?,?)";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, obj.getId());
            myStmt.setInt(2, obj.getTotalChildren());
            myStmt.setString(3, obj.getSpouseName());

            int affectedRows = myStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SeekerDAO.class.getName());
            logger.log(Level.SEVERE, "Exception generated while performing insert operation. " + e);
        }
    }

    @Override
    public void update(Seeker obj) {
        super.update(obj);

        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE seeker "
                + "SET total_children=?, spouse_name=? "
                + "WHERE seeker_id=?";

        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, obj.getTotalChildren());
            myStmt.setString(2, obj.getSpouseName());
            myStmt.setInt(3, obj.getId());

            int affectedRows = myStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected. ");
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SeekerDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing update operation. " + e);
        }
    }

    @Override
    public boolean delete(Serializable id) {
        return super.delete(id);
    }

    @Override
    public Seeker get(Serializable id) {
        Member member = super.get(id);
        Seeker seeker = null;
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "select * from seeker where seeker_id=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, (Integer) id);

            try (ResultSet myRs = myStmt.executeQuery()) {
                if (myRs.next()) {
                    int totalChildren = myRs.getInt("total_children");
                    String spouseName = myRs.getString("spouse_name");
                    seeker = new Seeker(member, totalChildren, spouseName);
                } else {
                    throw new SQLException("Could not find member with given userId");
                }
            }

        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "userId " + e);
        }
        return seeker;
    }
}
