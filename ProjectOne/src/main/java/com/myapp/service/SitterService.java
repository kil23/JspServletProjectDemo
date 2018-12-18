package com.myapp.service;

import java.util.List;

import com.myapp.app.form.JobAppform;
import com.myapp.dao.daolayer.JobAppDao;
import com.myapp.dao.daolayer.JobDao;
import com.myapp.dao.model.Job;
import com.myapp.dao.model.JobApplication;
import com.myapp.dao.model.JobApplication.Status;

public class SitterService {
	
	public static boolean isJobForUserid(int userid, int jobid) {
		List<Job> joblist = new JobDao().getUserJobs(userid);
		for(Job job : joblist) {
			if(job.getJobid() == jobid) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean applyJob(JobAppform jobAppform) {
		JobAppDao jobAppDao = new JobAppDao();
		int userid = Integer.parseInt(jobAppform.getMemberid());
		int jobid = Integer.parseInt(jobAppform.getJobid());
		double expectedpay = Double.parseDouble(jobAppform.getExpectedpay());
		Status status = jobAppform.getStatus();
		JobApplication jobApplication = new JobApplication(jobid, userid, expectedpay, status);
		return false;
	}
	
	public static Job getJobByJobid(int jobid) {
		JobDao jobDao = new JobDao();
		Job job = jobDao.getJob(jobid);
		return job;
	}
}
