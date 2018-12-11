package org.care.presentation.seeker;

import org.care.context.MyApplicationContext;
import org.care.dto.JobPostFormDTO;
import org.care.service.SeekerService;
import org.care.utils.CommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewJobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/seeker/newjob.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String title = req.getParameter("title");
        String startDate = req.getParameter("startdate");
        String endDate = req.getParameter("enddate");
        String payPerHour = req.getParameter("payperhour");

        JobPostFormDTO jobPostFormDTO = new JobPostFormDTO(title, userId, startDate, endDate, payPerHour);

        if (jobPostFormDTO.validate()) {
            boolean isPosted = SeekerService.postJob(jobPostFormDTO);
            if (isPosted) {
                resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/home?success=true"));
            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/home?success=false"));
            }
        } else {
            req.setAttribute("jobData", jobPostFormDTO);
            req.getRequestDispatcher("/WEB-INF/jsp/seeker/newjob.jsp").forward(req,resp);
        }
    }
}
