package org.care.presentation.seeker;

import org.care.context.MyApplicationContext;
import org.care.dto.SeekerJobDTO;
import org.care.service.SeekerService;
import org.care.utils.CommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditJobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobIdRaw = req.getParameter("JobId");
        if (jobIdRaw != null && !jobIdRaw.isEmpty() && jobIdRaw.matches("^[0-9]+$")) {
            int jobId = Integer.parseInt(jobIdRaw);

            if (userId == SeekerService.getUserIdforJobId(jobId)) {
                SeekerJobDTO seekerJobDTO = SeekerService.getJob(jobId);
                req.setAttribute("Job", seekerJobDTO);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/seeker/editjob.jsp");
                dispatcher.forward(req, resp);

            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/list-job?success=false"));
            }
        } else {
            resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/list-job?success=false"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobId = req.getParameter("jobid");
        String title = req.getParameter("title");
        String startDate = req.getParameter("startdate");
        String endDate = req.getParameter("enddate");
        String payPerHour = req.getParameter("payperhour");
        SeekerJobDTO jobDTO = new SeekerJobDTO(jobId, title, startDate, endDate, payPerHour);

        if (jobDTO.validate()) {
            boolean isUpdated = SeekerService.updateJob(userId, jobDTO);

            if (isUpdated) {
                resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/list-job?success=true"));
            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/list-job?success=false"));
            }
        } else {
            req.setAttribute("Job", jobDTO);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/seeker/editjob.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
