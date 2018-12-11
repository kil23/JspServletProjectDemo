package org.care.presentation.sitter;

import org.care.context.MyApplicationContext;
import org.care.service.SitterService;
import org.care.utils.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteJobApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobAppIdRaw = req.getParameter("JobApplicationId");
        if (jobAppIdRaw != null && !jobAppIdRaw.isEmpty() && jobAppIdRaw.matches("^[0-9]+$")) {
            int jobAppId = Integer.parseInt(jobAppIdRaw);

            if (userId == SitterService.getUserIdforJobAppId(jobAppId)) {
                boolean isDeleted = false;
                isDeleted = SitterService.deleteJobApplication(jobAppId);

                if (isDeleted) {
                    resp.sendRedirect("/HomeJobMarketplace/sitter/list-job-application?success=true");
                } else {
                    resp.sendRedirect("/HomeJobMarketplace/sitter/list-job-application?success=false");
                }
            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job-application?success=false"));
            }
        } else {
            resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job-application?success=false"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job-application"));
    }
}
