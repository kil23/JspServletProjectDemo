package com.myapp.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.myapp.app.form.Jobform;
import com.myapp.dao.daolayer.JobAppDao;
import com.myapp.dao.daolayer.JobDao;
import com.myapp.dao.model.Job;

public class SeekerService {
	
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
