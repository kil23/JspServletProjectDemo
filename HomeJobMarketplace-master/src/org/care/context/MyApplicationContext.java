package org.care.context;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.care.dao.DAO;
import org.care.model.Member;
import org.care.service.MemberService;

public class MyApplicationContext {

    private static DataSource dataSource;
    private Connection jdbcConnection;
    private static ThreadLocal<MyApplicationContext> appContextThreadLocal = new ThreadLocal<>();
    private static DAOFactory daoFactory = new DAOFactory();
    private HttpServletRequest httpRequest;

    private MyApplicationContext() {
    }

    public static MyApplicationContext get() {
        return appContextThreadLocal.get();
    }

    public static void initContext()
        throws ClassNotFoundException, SQLException, NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        // Look up our data source
        dataSource = (DataSource) envCtx.lookup("jdbc/HomeJobDB");
    }

    public static void destroyContext() throws SQLException{
        // doing nothing for now
    }

    public static void create(HttpServletRequest httpServletRequest) {
        if (appContextThreadLocal.get() != null) {
            throw new IllegalStateException("Thread local application context already exists!");
        }
        MyApplicationContext myContext = new MyApplicationContext();
        appContextThreadLocal.set(myContext);
        myContext.httpRequest = httpServletRequest;
        try {
            myContext.jdbcConnection = dataSource.getConnection();
        } catch (SQLException e) {
            Logger.getLogger(MyApplicationContext.class.getName())
                .log(Level.SEVERE, "Failed to open JDBC connection", e);
            throw new RuntimeException("Failed to open JDBC connection", e);
        }
    }

    public static void destroy() {
        MyApplicationContext myApplicationContext = appContextThreadLocal.get();
        if (myApplicationContext == null) {
            throw new IllegalStateException("Thread local application context is already destroyed!");
        }
        try {
            myApplicationContext.jdbcConnection.close();
        } catch (Exception e) {
            Logger.getLogger(MyApplicationContext.class.getName())
                .log(Level.SEVERE, "Failed to close JDBC connection", e);
            throw new RuntimeException("Failed to close JDBC connection", e);
        }
        appContextThreadLocal.remove();
    }

    public static <T extends DAO> T getFactory(Class<T> clazz) {
        //todo: include null check
        return daoFactory.getDAO(clazz);
    }

    public static Connection getJdbcConnection() {
        return get().getJDBCConnection();
    }

    public Connection getJDBCConnection() {
        return jdbcConnection;
    }

    public Member getMember() {
        Integer memberId = (Integer) httpRequest.getSession().getAttribute("UserId");
        if (memberId != null) {
            return MemberService.getMemberForId(memberId);
        }
        return null;
    }

    public String getAppURI() {
        return "/HomeJobMarketplace";
    }
}
