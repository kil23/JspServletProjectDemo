package com.myapp.app.form;

import java.util.HashMap;

import com.myapp.dao.model.JobApplication.Status;

public class Jobform {

	private int jobid;
	private String title;
    private int postedBy;
    private String startDate;
	private String endDate;
    private String payPerHr;
    private Status status;
    
    public Jobform(String title, int postedBy, String startDate, String endDate, Status status, String payPerHr) {
        this.title = title;
        this.postedBy = postedBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.setStatus(status);
        this.payPerHr = payPerHr;
    }
    
    public Jobform(int jobid, String title, int postedBy, String startDate, String endDate, Status status, String payPerHr) {
    	this.jobid = jobid;
    	this.title = title;
        this.postedBy = postedBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.setStatus(status);
        this.payPerHr = payPerHr;
    }
    
    public static HashMap<String, String> AuthenticateJob(){
    	HashMap<String,String> errorsjob = new HashMap<>();
    	
    	
    	
    	return errorsjob;
    }
    
    public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPayPerHr() {
		return payPerHr;
	}
	public void setPayPerHr(String payPerHr) {
		this.payPerHr = payPerHr;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

}
