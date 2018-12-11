package org.care.model;

import java.sql.Timestamp;

public class Job {
    private int id;
    private String title;
    private int postedBy;
    private Timestamp startDate;
    private Timestamp endDate;
    private double payPerHour;
    private Status status;

    public Job(int id, String title, Timestamp startDate, Timestamp endDate, double payPerHour) {
        this.id = id;
        this.title = title;
        this.postedBy = 0;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payPerHour = payPerHour;
    }

    public Job(String title, int postedBy, Timestamp startDate, Timestamp endDate, double payPerHour) {
        this.title = title;
        this.postedBy = postedBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payPerHour = payPerHour;
        this.status = Status.ACTIVE;
    }

    public Job(int id, String title, int postedBy, Timestamp startDate, Timestamp endDate, double payPerHour) {
        this(title, postedBy, startDate, endDate, payPerHour);
        this.id = id;
    }

    public Job(int id, String title, int postedBy, Timestamp startDate, Timestamp endDate, double payPerHour, Status status) {
        this.id = id;
        this.title = title;
        this.postedBy = postedBy;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {ACTIVE, INACTIVE}
}
