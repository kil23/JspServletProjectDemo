package com.myapp.app.form;

import java.util.HashMap;

import com.myapp.dao.model.JobApplication.Status;

public class JobAppform {
	
	private String id;
	private String jobid;
	private String memberid;
	private String expectedpay;
	private Status status;
	
	public JobAppform(String id, String jobid, String memberid, String expectedpay, Status status){
		this.id = id;
		this.jobid = jobid;
		this.memberid = memberid;
		this.expectedpay = expectedpay;
		this.status = status;
	}
	
	public JobAppform(String jobid, String memberid, String expectedpay, Status status) {
		this.jobid = jobid;
		this.memberid = memberid;
		this.expectedpay = expectedpay;
		this.status = status;
	}
	
	HashMap<String, String> errorsJobApp = new HashMap<>();

	public HashMap<String, String> Authenticate(){
		return errorsJobApp;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getExpectedpay() {
		return expectedpay;
	}

	public void setExpectedpay(String expectedpay) {
		this.expectedpay = expectedpay;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
