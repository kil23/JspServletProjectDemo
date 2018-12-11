package org.care.listeners;

import org.care.context.MyApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            MyApplicationContext.initContext();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(ApplicationListener.class.getName());
            logger.log(Level.INFO, "Database connection opening failed while opening: " + e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            MyApplicationContext.destroyContext();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(ApplicationListener.class.getName());
            logger.log(Level.INFO, "Database connection failed while closing: " + e);
        }
    }
}
