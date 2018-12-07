package com.myapp.dao.model;

public class Job{
	
	private int jobid;
	private String title;
	private int postedBy;
	private DateTime startDateTime;
	private DateTime endDateTime;
	private float payPerHr;
	
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
	public DateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(DateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public DateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(DateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	public float getPayPerHr() {
		return payPerHr;
	}
	public void setPayPerHr(float payPerHr) {
		this.payPerHr = payPerHr;
	}
	
	public Job(int jobid, String title, int postedBy, DateTime startDateTime, DateTime endDateTime, float payPerHr) {
		super();
		this.jobid = jobid;
		this.title = title;
		this.postedBy = postedBy;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.payPerHr = payPerHr;
	}
	
	public Job() { }
}
