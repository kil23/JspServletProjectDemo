package com.myapp.dao.daolayer;

import java.util.List;

import com.myapp.dao.model.Job;
import com.myapp.dao.model.JobApplication;

public interface JobAppDaoInterf {
	
	JobApplication getJobAppUsingAppId(int jobId);
	List<JobApplication> getAppByUserIdNStatus(int userId, JobApplication.Status status);
	List<JobApplication> getAppUsingUidNnumResults(int userId, int numResults);
	List<JobApplication> getUsingUserid(int userId);
	List<JobApplication> getUsingUseridNStatus(int userId, JobApplication.Status status);
	boolean insertUsingJobApp(JobApplication jobApp);
	boolean updateUsingJobApp(JobApplication jobApp);
	int deleteUsingJobId(int jobId);
	int deleteUsingUserId(int userId);
	
}
