package org.care.dto;

import org.care.model.Job;
import org.care.utils.JobUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerJobDTO {
    private String id;
    private String title;
    private Job.Status status;
    private String startDate;
    private String endDate;
    private String payPerHour;
    private Map<String, String> errors;

    public SeekerJobDTO(String id, String title, Job.Status status, String startDate, String endDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SeekerJobDTO(String id, String title, String startDate, String endDate, String payPerHour) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payPerHour = payPerHour;
    }

    public SeekerJobDTO(String id, String title, Job.Status status, String startDate, String endDate, String payPerHour) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payPerHour = payPerHour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Job.Status getStatus() {
        return status;
    }

    public void setStatus(Job.Status status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(String payPerHour) {
        this.payPerHour = payPerHour;
    }

    public boolean validate () {
        errors = new HashMap<>();
        validateJobId();
        validateTitle();
        validateStartDate();
        validateEndDate();
        validatePayPerHour();
        return errors.isEmpty();
    }

    private void validateJobId() {
        if (id == null) {
            errors.put("id", "Go back to homepage!");
        } else if (id.isEmpty()) {
            errors.put("id", "Go back to homepage!");
        }
    }

    private void validateTitle() {
        if (title.isEmpty()) {
            errors.put("title", "Can't be empty!");

        } else if(JobUtil.checkForBadWords(title)) {
            errors.put("title", "Please, don't use bad words");
        }
    }

    private void validateStartDate() {
        if (startDate == null){
            errors.put("startDate", "Can't be empty!");
        } else if (startDate.isEmpty()) {
            errors.put("startDate", "Can't be empty!");
        } else {
            try {
                Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                if (sDate.before(new Date())) {
                    errors.put("startDate", "Start Date should be greater than today's date.");
                }
            } catch (ParseException e) {
                Logger logger = Logger.getLogger(JobPostFormDTO.class.getName());
                logger.log(Level.SEVERE, "Cannot convert html date into java date format");
            }
        }
    }

    private void validateEndDate() {
        if (endDate == null) {
            errors.put("endDate", "Can't be empty!");
        } else if (endDate.isEmpty()) {
            errors.put("endDate", "Can't be empty!");
        } else {
            try {
                Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
                if (eDate.before(sDate)) {
                    errors.put("endDate", "End Date should be greater than start date.");
                }
            } catch (ParseException e) {
                Logger logger = Logger.getLogger(JobPostFormDTO.class.getName());
                logger.log(Level.SEVERE, "Cannot convert html date into java date format");
            }
        }
    }

    private void validatePayPerHour() {
        if (payPerHour.isEmpty()) {
            errors.put("payPerHour", "Can't be empty");
        } else if(!payPerHour.matches("^([0-9]+)$|^([0-9]+\\.[0-9]+)$")) {
            errors.put("payPerHour", "Can only contain one decimal!");
        } else if(!(Double.parseDouble(payPerHour) >= 0) ) {
            errors.put("payPerHour", "no. can't be negative!");
        }
    }
}
