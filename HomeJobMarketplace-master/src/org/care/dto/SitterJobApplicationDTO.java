package org.care.dto;

import org.care.model.JobApplication;

public class SitterJobApplicationDTO {
    private int id;
    private String title;
    private double expectedPay;
    private double payPerHour;
    private JobApplication.Status status;

    public SitterJobApplicationDTO(int id, String title, double expectedPay, double payPerHour, JobApplication.Status status) {
        this.id = id;
        this.title = title;
        this.expectedPay = expectedPay;
        this.payPerHour = payPerHour;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public JobApplication.Status getStatus() {
        return status;
    }

    public void setStatus(JobApplication.Status status) {
        this.status = status;
    }
}
