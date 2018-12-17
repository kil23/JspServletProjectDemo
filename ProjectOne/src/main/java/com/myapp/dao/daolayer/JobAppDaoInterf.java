package com.myapp.dao.daolayer;

import java.util.List;

import com.myapp.dao.model.Job;
import com.myapp.dao.model.JobApplication;

public interface JobAppDaoInterf {
	
	List<JobApplication> getJobAppListByJobId(int jobId);
	List<JobApplication> getJobAppListByUserIdNStatus(int userId, JobApplication.Status status);
	List<JobApplication> getJobAppListUsingUidNnumResults(int userId, int numResults);
	List<JobApplication> getJobAppListUsingUserid(int userId);
	List<JobApplication> getJobAppListUsingUseridNStatus(int userId, JobApplication.Status status);
	boolean insertUsingJobApp(JobApplication jobApp);
	boolean updateUsingJobApp(JobApplication jobApp);
	int deleteUsingJobId(int jobId);
	int deleteUsingUserId(int userId);
	
}
