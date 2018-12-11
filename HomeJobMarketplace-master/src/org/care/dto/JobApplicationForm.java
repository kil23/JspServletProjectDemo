package org.care.dto;

import java.util.HashMap;
import java.util.Map;

public class JobApplicationForm {
    private int userId;
    private String jobId;
    private String expectedPay;
    private Map<String, String> errors;

    public JobApplicationForm(int userId, String jobId, String expectedPay) {
        this.userId = userId;
        this.jobId = jobId;
        this.expectedPay = expectedPay;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    public boolean validate() {
        errors = new HashMap<>();
        validateJobId();
        validateExpectedPay();

        return errors.isEmpty();
    }

    private void validateJobId() {
        if (jobId == null) {
            errors.put("jobId", "Invalid job id!");
        } else if (jobId.isEmpty()) {
            errors.put("jobId", "Can't be empty!");
        } else if (!jobId.matches("^[0-9]+$")) {
            errors.put("jobId", "Invalid job id!");
        }
    }

    private void validateExpectedPay() {
        if (expectedPay.isEmpty()) {
            errors.put("expectedPay", "Can't be empty!");
        } else if (!expectedPay.matches("^([0-9]+)$|^([0-9]+\\.[0-9]+)$")) {
            errors.put("expectedPay", "Incorrect Input!");
        } else if (!(Double.parseDouble(expectedPay) >= 0)) {
            errors.put("expectedPay", "Can't be negative!");
        }
    }
}
