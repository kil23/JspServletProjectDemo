package org.care.model;

public class JobApplication {
    private int id;
    private int jobId;
    private int memberId;
    private double expectedPay;
    private Status status;

    public JobApplication(int id, int jobId, int memberId, double expectedPay) {
        this.id = id;
        this.jobId = jobId;
        this.memberId = memberId;
        this.expectedPay = expectedPay;
        this.status = Status.ACTIVE;
    }

    public JobApplication(int id, int jobId, int memberId, double expectedPay, Status status) {
        this.id = id;
        this.jobId = jobId;
        this.memberId = memberId;
        this.expectedPay = expectedPay;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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

    public enum Status {ACTIVE, INACTIVE}
}
