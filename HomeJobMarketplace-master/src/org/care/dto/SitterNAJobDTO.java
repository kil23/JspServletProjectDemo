package org.care.dto;

import java.util.Date;

public class SitterNAJobDTO {
    private int jobId;
    private String title;
    private Double payPerHour;
    private Date startDate;

    public SitterNAJobDTO(int jobId, String title, Double payPerHour, Date startDate) {
        this.jobId = jobId;
        this.title = title;
        this.payPerHour = payPerHour;
        this.startDate = startDate;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(Double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
