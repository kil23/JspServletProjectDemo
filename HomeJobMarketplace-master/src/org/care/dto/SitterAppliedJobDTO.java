package org.care.dto;

import java.sql.Timestamp;

public class SitterAppliedJobDTO {
    private String title;
    private Timestamp startDate;
    private double expectedPay;

    public SitterAppliedJobDTO(String title, Timestamp startDate, double expectedPay) {
        this.title = title;
        this.startDate = startDate;
        this.expectedPay = expectedPay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }
}
