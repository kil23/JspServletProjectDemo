package org.care.presentation.seeker;

import org.care.context.MyApplicationContext;
import org.care.dto.SeekerJobApplicationDTO;
import org.care.service.SeekerService;
import org.care.utils.CommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListSeekerJobApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobIdRaw = req.getParameter("JobId");
        if (jobIdRaw != null && !jobIdRaw.isEmpty() && jobIdRaw.matches("^[0-9]+$")) {
            int jobId = Integer.parseInt(jobIdRaw);

            if (userId == SeekerService.getUserIdforJobId(jobId)) {

                String jobTitle = SeekerService.getJobTitle(jobId);
                List<SeekerJobApplicationDTO> seekerJobAppDTOS = SeekerService.getJobApplicationsByJobId(jobId);
                req.setAttribute("jobApplications", seekerJobAppDTOS);
                req.setAttribute("jobTitle", jobTitle);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/seeker/jobapplicationlist.jsp");
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
        resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/list-job-application"));
    }
}
