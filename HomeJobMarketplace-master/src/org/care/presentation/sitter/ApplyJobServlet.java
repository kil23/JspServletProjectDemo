package org.care.presentation.sitter;

import org.care.context.MyApplicationContext;
import org.care.dto.JobApplicationForm;
import org.care.dto.SitterNAJobDTO;
import org.care.service.SitterService;
import org.care.utils.CommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ApplyJobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobIdRaw = req.getParameter("JobId");
        if (jobIdRaw != null && !jobIdRaw.isEmpty() && jobIdRaw.matches("^[0-9]+$")) {
            int jobId = Integer.parseInt(jobIdRaw);

            if (SitterService.isJobInNAJobsList(jobId, userId)) {

                req.setAttribute("JobId", jobId);
                String jobTitle = SitterService.getJobTitle(jobId);
                req.setAttribute("Title", jobTitle);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/sitter/applyjob.jsp");
                dispatcher.forward(req, resp);

            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job"));
            }
        } else {
            resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobId = req.getParameter("jobid");
        String expectedPay = req.getParameter("expectedpay");

        JobApplicationForm jobApplicationForm = new JobApplicationForm(userId, jobId, expectedPay);

        if (jobApplicationForm.validate()) {
            boolean isApplied = SitterService.applyJob(jobApplicationForm);
            if (isApplied) {
                resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/home?success=true"));
            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/home?success=false"));
            }
        } else {
            req.setAttribute("JobId", jobId);
            String jobTitle = SitterService.getJobTitle(Integer.parseInt(jobId));
            req.setAttribute("Title", jobTitle);
            req.setAttribute("jobApplication", jobApplicationForm);
            req.getRequestDispatcher("/WEB-INF/jsp/sitter/applyjob.jsp").forward(req,resp);
        }
    }
}
