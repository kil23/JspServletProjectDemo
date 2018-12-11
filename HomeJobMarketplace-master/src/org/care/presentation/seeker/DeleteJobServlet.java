package org.care.presentation.seeker;

import org.care.context.MyApplicationContext;
import org.care.service.MemberService;
import org.care.service.SeekerService;
import org.care.utils.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteJobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobIdRaw = req.getParameter("JobId");
        if (jobIdRaw != null && !jobIdRaw.isEmpty() && jobIdRaw.matches("^[0-9]+$")) {
            int jobId = Integer.parseInt(jobIdRaw);

            boolean isDeleted = false;
            if(userId == SeekerService.getUserIdforJobId(jobId)) {
                isDeleted = SeekerService.deleteJob(jobId);
            }

            if (isDeleted) {
                resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/list-job?success=true"));
            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/seeker/list-job?success=false"));
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(CommonUtil.getRedirectURL(""));
    }
}
