package com.myapp.dao.daolayer;

import java.util.List;

import com.myapp.dao.model.Job;

public interface JobDaoInterf {
	
	Job getJob(int jobId);
	boolean insertJob(Job job);
	boolean updateJob(Job job);
	boolean deleteJob(int jobid);
	List<?> getUserJob(int userid);
	
}
