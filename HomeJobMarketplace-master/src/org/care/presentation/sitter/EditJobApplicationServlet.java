package org.care.presentation.sitter;

import org.care.context.MyApplicationContext;
import org.care.service.SitterService;
import org.care.utils.CommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditJobApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String jobAppIdRaw = req.getParameter("JobApplicationId");
        if (jobAppIdRaw != null && !jobAppIdRaw.isEmpty() && jobAppIdRaw.matches("^[0-9]+$")) {
            int jobAppId = Integer.parseInt(jobAppIdRaw);

            if (userId == SitterService.getUserIdforJobAppId(jobAppId)) {

                String title = SitterService.getJobApplicationTitle(jobAppId);

                req.setAttribute("JobApplicationId", jobAppId);
                req.setAttribute("ExpectedPay", SitterService.getJobAppExpPay(jobAppId));
                req.setAttribute("Title", title);

                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/sitter/editjobapplication.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job-application?success=false"));
            }
        } else {
            resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job-application?success=false"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int jobAppId = Integer.parseInt(req.getParameter("jobappid"));
        double expectedPay = Double.parseDouble(req.getParameter("expectedpay"));

        boolean isUpdated = SitterService.updateJobApplication(jobAppId, expectedPay);

        if (isUpdated) {
            resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job-application?success=true"));
        } else {
            resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/list-job-application?success=false"));
        }
    }
}
