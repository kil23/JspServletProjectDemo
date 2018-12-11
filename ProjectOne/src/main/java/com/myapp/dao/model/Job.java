package com.myapp.dao.model;

import java.sql.Timestamp;

import com.myapp.dao.model.JobApplication.Status;

public class Job{
	
	private int jobid;
	private String title;
	private int postedBy;
	private Timestamp startDate;
	private Timestamp endDate;
	private double payPerHr;
	private Status status;
	
	public Job(int jobid, String title, Timestamp startDate, Timestamp endDate, double payPerHr) {
		this.jobid = jobid;
		this.title = title;
		this.postedBy = 0;
		this.startDate = startDate;
		this.endDate = endDate;
		this.payPerHr = payPerHr;
	}
	
	public Job(String title, int postedBy, Timestamp startDate, Timestamp endDate, double payPerHr) { 
		this.title = title;
		this.postedBy = postedBy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.payPerHr = payPerHr;
		this.status = Status.Active;
	}
	
	public Job(int jobid, String title, int postedBy, Timestamp startDate, Timestamp endDate, double payPerHr) {
		this(title, postedBy, startDate, endDate, payPerHr);
		this.jobid = jobid;
	}
	
	public Job() { }
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public double getPayPerHr() {
		return payPerHr;
	}
	public void setPayPerHr(double payPerHr) {
		this.payPerHr = payPerHr;
	}
}
