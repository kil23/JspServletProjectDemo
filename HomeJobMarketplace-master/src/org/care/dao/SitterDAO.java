package org.care.dao;

import org.care.context.MyApplicationContext;
import org.care.model.Member;
import org.care.model.Sitter;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SitterDAO extends MemberDAO<Sitter> {

    @Override
    public void create(Sitter obj) {
        //inserting data into member first
        super.create(obj);

        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "insert into sitter "
                + "(sitter_id, experience)"
                + "values(?,?)";

        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setString(1, String.valueOf(obj.getId()));
            myStmt.setString(2, String.valueOf(obj.getExperience()));

            int affectedRows = myStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SitterDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing insert operation" + e);
        }
    }

    @Override
    public void update(Sitter obj) {
        super.update(obj);

        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "UPDATE sitter "
                + "SET experience=? "
                + "WHERE sitter_id=?";

        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, obj.getExperience());
            myStmt.setInt(2, obj.getId());

            int affectedRows = myStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected. ");
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SitterDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing update operation. " + e);
        }
    }

    @Override
    public boolean delete(Serializable id) {
        return super.delete(id);
    }

    @Override
    public Sitter get(Serializable id) {
        Member member = super.get(id);
        Sitter sitter = null;
        Connection myConn = MyApplicationContext.getJdbcConnection();
        String sql = "select * from sitter where sitter_id=?";
        try (PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setInt(1, (Integer) id);

            try (ResultSet myRs = myStmt.executeQuery()) {
                if (myRs.next()) {
                    int experience = myRs.getInt("experience");
                    sitter = new Sitter(member, experience);
                } else {
                    throw new SQLException("Could not find sitter with given userId");
                }
            }

        } catch (Exception e) {
            Logger logger = Logger.getLogger(MemberDAO.class.getName());
            logger.log(Level.SEVERE, "exception while performing retrieve operation using " +
                    "userId " + e);
        }
        return sitter;
    }
}
