package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import com.myapp.app.form.JobAppform;
import com.myapp.app.form.Jobform;
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

	public static boolean updateJobApplication(JobAppform jobAppform){
		JobAppDao jobAppDao = new JobAppDao();
		JobApplication application = new JobApplication();
		application.setJobApplicationId(Integer.parseInt(jobAppform.getId()));
		application.setExpectedPay(Double.parseDouble(jobAppform.getExpectedpay()));
		return jobAppDao.updateUsingJobApp(application);
	}

	public static boolean applyJob(JobAppform jobAppform) {
		JobAppDao jobAppDao = new JobAppDao();
		int userid = Integer.parseInt(jobAppform.getMemberid());
		int jobid = Integer.parseInt(jobAppform.getJobid());
		double expectedpay = Double.parseDouble(jobAppform.getExpectedpay());
		Status status = jobAppform.getStatus();
		JobApplication jobApplication = new JobApplication(jobid, userid, expectedpay, status);
		jobAppDao.insertUsingJobApp(jobApplication);
		return false;
	}
	
	public static Job getJobByJobid(int jobid) {
		JobDao jobDao = new JobDao();
		Job job = jobDao.getJob(jobid);
		return job;
	}
	
	public static List<Jobform> getJobListByUserid(int userid){
		JobDao jobDao = new JobDao();
		List<Jobform> jobform = new ArrayList<>();
		List<Job> joblist = jobDao.getUserJobs(userid);
		for(Job job : joblist) {
			int jobid = job.getJobid();
			String title = job.getTitle();
			int postedBy = userid;
			String startDate = String.valueOf(job.getStartDate());
			String endDate = String.valueOf(job.getEndDate());
			String payPerHr = String.valueOf(job.getPayPerHr());
			Status status = job.getStatus();
			jobform.add(new Jobform(jobid, title, postedBy, startDate, endDate, status, payPerHr));
		}
		return jobform;	
	}
	
	public static List<JobAppform> getJobApplicationListByUserid(int userid){
		List<JobAppform> listform = new ArrayList<>();
		JobAppDao jobAppDao = new JobAppDao();
		List<JobApplication> jobApplications = jobAppDao.getJobAppListByUserid(userid);
		for(JobApplication jobApp : jobApplications) {
			String id = String.valueOf(jobApp.getJobApplicationId());
			String jobid = String.valueOf(jobApp.getJobid());
			String memid = String.valueOf(userid);
			String expectedpay = String.valueOf(jobApp.getExpectedPay());
			Status status = jobApp.getStatus();
			listform.add(new JobAppform(id, jobid, memid, expectedpay, status));
		}
		return listform;
	}
	
	public static JobApplication getJobAppUsingJobAppid(int jobAppid) {
		JobAppDao appDao = new JobAppDao();
		JobApplication application = appDao.getJobAppByJobAppId(jobAppid);
		return application;
	}

	public static String getJobApplicationTitleByJobAppid(int jobAppid){
		JobAppDao jobAppDao = new JobAppDao();
		JobApplication jobApplication = jobAppDao.getJobAppByJobAppId(jobAppid);
		JobDao jobDao = new JobDao();
		Job job = jobDao.getJob(jobApplication.getJobid());
		return job.getTitle();
	}

	public static boolean deleteJobApp(int JobAppid) {
		JobAppDao jobAppDao = new JobAppDao();
		boolean result = jobAppDao.deleteJobAppUsingJobAppId(JobAppid);
		return result;
	}
}
