package com.myapp.dao.daolayer;

import com.myapp.dao.model.JobApplication;

public interface JobAppDaoInterf {
	
	JobApplication getJobApp(int jobAppId);
	boolean insertJobApp(JobApplication j);
	boolean updateJobApp(JobApplication j);
	boolean deleteJobApp(int jobAppId);
	
}
