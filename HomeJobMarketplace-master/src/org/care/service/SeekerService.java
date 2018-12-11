package org.care.service;

import org.care.context.MyApplicationContext;
import org.care.dao.JobApplicationDAO;
import org.care.dao.JobDAO;
import org.care.dao.SeekerDAO;
import org.care.dto.*;
import org.care.model.Job;
import org.care.model.JobApplication;
import org.care.model.Seeker;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerService {

    public static void register(SeekerRegistrationFormDTO seekerFormData) {
        SeekerDAO seekerDAO = MyApplicationContext.getFactory(SeekerDAO.class);
        String firstName = seekerFormData.getFirstName();
        String lastName = seekerFormData.getLastName();
        String phoneNo = seekerFormData.getPhoneNo();
        String emailId = seekerFormData.getEmailId();
        String password = seekerFormData.getPassword();
        String address = seekerFormData.getAddress();
        int pincode = Integer.parseInt(seekerFormData.getPincode());
        int totalChildren = Integer.parseInt(seekerFormData.getTotalChildren());
        String spouseName = seekerFormData.getSpouseName();
        Seeker seeker = new Seeker(firstName, lastName, phoneNo, emailId, password, address, pincode, totalChildren, spouseName);
        seekerDAO.create(seeker);
    }

    public static List<JobApplication> getJobApplications(int userId, int noOfResults) {

        JobApplicationDAO jobApplicationDAO = MyApplicationContext.getFactory(JobApplicationDAO.class);
        return jobApplicationDAO.get(userId, noOfResults);
    }

    public static List<JobApplication> getJobApplications(int userId) {
        JobApplicationDAO jobApplicationDAO = MyApplicationContext.getFactory(JobApplicationDAO.class);
        return jobApplicationDAO.get(userId);
    }

    public static List<JobApplication> getJobApplications(int userId, JobApplication.Status status) {
        JobApplicationDAO jobApplicationDAO = MyApplicationContext.getFactory(JobApplicationDAO.class);
        return jobApplicationDAO.get(userId, status);
    }

    public static boolean postJob(JobPostFormDTO jobPostFormDTO) {
        try {
            JobDAO jobDAO = MyApplicationContext.getFactory(JobDAO.class);
            String title = jobPostFormDTO.getTitle();
            int postedBy = jobPostFormDTO.getPostedBy();
            Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(jobPostFormDTO.getStartDate());
            Timestamp startDate = new Timestamp(sDate.getTime());
            Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(jobPostFormDTO.getEndDate());
            Timestamp endDate = new Timestamp(eDate.getTime());
            Double payPerHour = Double.parseDouble(jobPostFormDTO.getPayPerHour());
            Job job = new Job(title, postedBy, startDate, endDate, payPerHour);
            jobDAO.create(job);
            return true;
        } catch (ParseException e) {
            Logger logger = Logger.getLogger(SeekerService.class.getName());
            logger.log(Level.SEVERE, "Cannot convert html date to java date format. ");
            return false;
        }
    }

    public static SeekerProfileDTO getProfile(int userId) {
        SeekerDAO seekerDAO = MyApplicationContext.getFactory(SeekerDAO.class);
        Seeker seeker = seekerDAO.get(userId);

        String firstName = seeker.getFirstName();
        String lastName = seeker.getLastName();
        String phoneNo = seeker.getPhoneNo();
        String emailId = seeker.getEmailId();
        String address = seeker.getAddress();
        String pincode = String.valueOf(seeker.getPincode());
        String totalChildren = String.valueOf(seeker.getTotalChildren());
        String spouseName = seeker.getSpouseName();

        return new SeekerProfileDTO(firstName, lastName, phoneNo, emailId, address, pincode, totalChildren, spouseName);
    }

    public static List<SeekerJobDTO> getJobsList(int userId) {
        List<SeekerJobDTO> seekerJobDTOS = new LinkedList<>();
        List<Job> jobsList;
        JobDAO jobDAO = MyApplicationContext.getFactory(JobDAO.class);
        jobsList = jobDAO.getJobsPostedBy(userId);
        for (Job tempJob :
                jobsList) {
            String id = String.valueOf(tempJob.getId());
            String title = tempJob.getTitle();
            Job.Status status = tempJob.getStatus();
            String startDate = String.valueOf(tempJob.getStartDate());
            String endDate = String.valueOf(tempJob.getEndDate());
            seekerJobDTOS.add(new SeekerJobDTO(id, title, status, startDate, endDate));
        }
        return seekerJobDTOS;
    }

    public static void updateProfile(int userId, SeekerProfileDTO seekerProfileDTO) {
        SeekerDAO seekerDAO = MyApplicationContext.getFactory(SeekerDAO.class);
        Seeker seeker = seekerDAO.get(userId);
        seeker.setFirstName(seekerProfileDTO.getFirstName());
        seeker.setLastName(seekerProfileDTO.getLastName());
        seeker.setPhoneNo(seekerProfileDTO.getPhoneNo());
        seeker.setEmailId(seekerProfileDTO.getEmailId());
        seeker.setAddress(seekerProfileDTO.getAddress());
        seeker.setPincode(Integer.parseInt(seekerProfileDTO.getPincode()));
        seeker.setTotalChildren(Integer.parseInt(seekerProfileDTO.getTotalChildren()));
        seeker.setSpouseName(seekerProfileDTO.getSpouseName());

        seekerDAO.update(seeker);
    }

    public static SeekerJobDTO getJob(int jobId) {
        JobDAO jobDAO = MyApplicationContext.getFactory(JobDAO.class);
        Job job = jobDAO.get(jobId);
        String id = String.valueOf(job.getId());
        String title = job.getTitle();
        Job.Status status = job.getStatus();
        String startDate = new SimpleDateFormat("yyyy-MM-dd").format(job.getStartDate());
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(job.getEndDate());
        String payPerHour = String.valueOf(job.getPayPerHour());
        return new SeekerJobDTO(id, title, status, startDate, endDate, payPerHour);
    }

    public static boolean updateJob(int userId, SeekerJobDTO jobDTO) {
        try {
            JobDAO jobDAO = MyApplicationContext.getFactory(JobDAO.class);
            int id = Integer.parseInt(jobDTO.getId());
            String title = jobDTO.getTitle();
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(jobDTO.getStartDate());
            Timestamp startDateTS = new Timestamp(startDate.getTime());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(jobDTO.getEndDate());
            Timestamp endDateTS = new Timestamp(endDate.getTime());
            double payPerHour = Double.parseDouble(jobDTO.getPayPerHour());

            jobDAO.update(new Job(id, title, userId, startDateTS, endDateTS, payPerHour));
            return true;

        } catch (ParseException e) {
            Logger logger = Logger.getLogger(SeekerService.class.getName());
            logger.log(Level.SEVERE, "Cannot convert html date to java date format. ");
            return false;
        }
    }

    public static boolean deleteJob(int jobId) {
        JobApplicationDAO jobApplicationDAO = MyApplicationContext.getFactory(JobApplicationDAO.class);
        int totalDeletedApplicaitons = jobApplicationDAO.deleteByJobId(jobId);

        boolean isJobDeleted = false;
        if (totalDeletedApplicaitons >= 0) {
            JobDAO jobDAO = MyApplicationContext.getFactory(JobDAO.class);
            isJobDeleted = jobDAO.delete(jobId);
        }

        return isJobDeleted;
    }

    public static List<SeekerJobApplicationDTO> getJobApplicationsByJobId(int jobId) {
        JobApplicationDAO jobApplicationDAO = MyApplicationContext.getFactory(JobApplicationDAO.class);
        List<Map<String, Object>> jobApplicaitons = jobApplicationDAO.getAllByJobId(jobId);
        List<SeekerJobApplicationDTO> seekerJobApplicationDTOS = new LinkedList<>();
        for (Map<String, Object> tempApp :
                jobApplicaitons) {
            int id = (int) tempApp.get("id");
            String firstName = (String) tempApp.get("firstName");
            JobApplication.Status status = (JobApplication.Status) tempApp.get("status");
            double expectedPay = (double) tempApp.get("expectedPay");
            seekerJobApplicationDTOS.add(new SeekerJobApplicationDTO(id, firstName, status, expectedPay));
        }
        return seekerJobApplicationDTOS;
    }

    public static String getJobTitle(int jobId) {
        JobDAO jobDAO = MyApplicationContext.getFactory(JobDAO.class);
        Job job = jobDAO.get(jobId);
        return job.getTitle();
    }

    public static int getUserIdforJobId(int jobId) {
        JobDAO jobDAO = MyApplicationContext.getFactory(JobDAO.class);
        Job job = jobDAO.get(jobId);
        return job.getPostedBy();
    }
}
