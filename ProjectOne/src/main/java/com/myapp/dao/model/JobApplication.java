package com.myapp.dao.model;

public class JobApplication {

	private int jobApplicationId;
	private int jobid;
	private int memberid;
	private double expectedPay;
	private Status status;
	
	public enum Status{ Active, Inactive}
	
	public JobApplication(int jobApplicationId, int jobid, int memberid, double expectedPay, Status status) {
		super();
		this.jobApplicationId = jobApplicationId;
		this.jobid = jobid;
		this.memberid = memberid;
		this.expectedPay = expectedPay;
		this.status = status;
	}
	
	public JobApplication(int jobid, int memberid, double expectedPay, Status status) {
		
		super();
		this.jobid = jobid;
		this.memberid = memberid;
		this.expectedPay = expectedPay;
		this.status = status;
	}
	
	public JobApplication() { }
	
	public int getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(int jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
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
	public double getExpectedPay() {
		return expectedPay;
	}
	public void setExpectedPay(double expectedPay) {
		this.expectedPay = expectedPay;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
