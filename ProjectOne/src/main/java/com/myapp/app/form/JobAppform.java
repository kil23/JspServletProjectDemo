package com.myapp.app.form;

import com.myapp.dao.model.JobApplication.Status;

public class JobAppform {
	
	private int id;
	private int jobid;
	private int memberid;
	private double expectedpay;
	private Status status;
	
	public JobAppform(int id, int jobid, int memberid, double expectedpay, Status status){
		this.id = id;
		this.jobid = jobid;
		this.memberid = memberid;
		this.expectedpay = expectedpay;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public double getExpectedpay() {
		return expectedpay;
	}

	public void setExpectedpay(double expectedpay) {
		this.expectedpay = expectedpay;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

}
