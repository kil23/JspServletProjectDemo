package com.myapp.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.myapp.util.ContextConnectionUtil;

public class ApplicationListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
        try {
            ContextConnectionUtil.initContext();
        }catch (Exception e){
            Logger logg = Logger.getLogger(ApplicationListener.class.getName());
            logg.log(Level.INFO, "Failure in Establishing Database Connection at start."+ e);
        }
    }
    
    public void contextInitialized(ServletContextEvent sce)  { 
         try{
             ContextConnectionUtil.destroyContext();
         }catch (Exception e) {
             Logger logg = Logger.getLogger(ApplicationListener.class.getName());
             logg.log(Level.INFO, "Failure in Closing Database Connection in the end." + e);
         }
    }
	
}
