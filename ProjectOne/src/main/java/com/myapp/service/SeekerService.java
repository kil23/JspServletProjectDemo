package com.myapp.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myapp.app.form.JobAppform;
import com.myapp.app.form.Jobform;
import com.myapp.dao.daolayer.JobAppDao;
import com.myapp.dao.daolayer.JobDao;
import com.myapp.dao.daolayer.MemberDao;
import com.myapp.dao.model.Job;
import com.myapp.dao.model.JobApplication;
import com.myapp.dao.model.JobApplication.Status;
import com.myapp.dao.model.Member;

public class SeekerService {
	
	public static List<Jobform> getJobList(int userid){
		JobDao jobDao = new JobDao();
		List<Job> jobsList = jobDao.getUserJobs(userid);
		List<Jobform> seekerjobs = new ArrayList<>();
		for(Job job : jobsList) {
			int jobid = job.getJobid();
			String title = String.valueOf(job.getTitle());
			String startdate = String.valueOf(job.getStartDate());
			String enddate = String.valueOf(job.getEndDate());
			Status status = job.getStatus();
			String payperhr = String.valueOf(job.getPayPerHr());
			seekerjobs.add(new Jobform(jobid, title, userid, startdate, enddate, status, payperhr));
		}
		return seekerjobs;
	}
	
	public static String getNameByid(int userid) {
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.getMember(userid);
		String fname = member.getFname();
		String lname = member.getLname();
		String name = fname +" "+ lname;
		return name;
	}
	
	public static Job getJobByJobid(int jobid) {
		JobDao jobDao = new JobDao();
		Job job = jobDao.getJob(jobid);
		return job;
	}
	
	public static List<JobAppform> getJobAppByJobid(int jobid) {
		JobAppDao jobAppDao = new JobAppDao();
		List<JobApplication> jobAppList = jobAppDao.getJobAppListByJobId(jobid);
		List<JobAppform> form = new ArrayList<>();
		for(JobApplication jobApp : jobAppList) {
			String id = String.valueOf(jobApp.getJobApplicationId());
			String job_id = String.valueOf(jobid);
			String memberid = String.valueOf(jobApp.getMemberid());
			Status status = jobApp.getStatus();
			String expectedpay = String.valueOf(jobApp.getExpectedPay());
			form.add(new JobAppform(id, job_id, memberid, expectedpay, status));
		}
		return form;
	}
	
	public static boolean postJob(Jobform jobform) {
		try {
			JobDao jobDao = new JobDao();
			
			String title = jobform.getTitle();
			int postedBy = jobform.getPostedBy();
			Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(jobform.getStartDate());
			Timestamp startdate = new Timestamp(sdate.getTime());
			Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(jobform.getEndDate());
			Timestamp enddate = new Timestamp(edate.getTime());
			Double payPerHr = Double.valueOf(jobform.getPayPerHr());
			
			Job job = new Job(title, postedBy, startdate, enddate, payPerHr);
			boolean jobposted = jobDao.insertJob(job);
			return jobposted;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public static boolean updateJob(int userid, Jobform jobform) {
		try {
			JobDao jobDao = new JobDao();
			
			int jobid = jobform.getJobid();
			String title = jobform.getTitle();
			int postedBy = jobform.getPostedBy();
			Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(jobform.getStartDate());
			Timestamp startdate = new Timestamp(sdate.getTime());
			Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(jobform.getEndDate());
			Timestamp enddate = new Timestamp(edate.getTime());
			Double payPerHr = Double.valueOf(jobform.getPayPerHr());
			
			Job job = new Job(jobid, title, postedBy, startdate, enddate, payPerHr);
			boolean jobupdated = jobDao.updateJob(job);
			return jobupdated;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public static boolean deleteJob(int jobid) {
		JobAppDao jobAppDao = new JobAppDao();
		int deletedApplication = jobAppDao.deleteUsingJobId(jobid);
		
		boolean jobDeleted = false;
		if(deletedApplication >= 0) {
			JobDao jobDao = new JobDao();
			jobDeleted = jobDao.deleteJob(jobid);
		}
		return jobDeleted;
		
	}
}
