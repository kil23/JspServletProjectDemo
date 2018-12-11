package org.care.dto;

import org.care.model.JobApplication;

public class SeekerJobApplicationDTO {
    private int id;
    private String sitterName;
    private JobApplication.Status status;
    private double expectedPay;

    public SeekerJobApplicationDTO(int id, String sitterName, JobApplication.Status status, double expectedPay) {
        this.id = id;
        this.sitterName = sitterName;
        this.status = status;
        this.expectedPay = expectedPay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSitterName() {
        return sitterName;
    }

    public void setSitterName(String sitterName) {
        this.sitterName = sitterName;
    }

    public JobApplication.Status getStatus() {
        return status;
    }

    public void setStatus(JobApplication.Status status) {
        this.status = status;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }
}
