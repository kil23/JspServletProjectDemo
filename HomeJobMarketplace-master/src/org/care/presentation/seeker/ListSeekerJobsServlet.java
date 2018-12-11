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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListSeekerJobsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = MyApplicationContext.get().getMember().getId();

        String success = req.getParameter("success");
        if(success != null) {
            if(success.equalsIgnoreCase("true")) {
                req.setAttribute("msg", "Operation Successful!");
            } else if (success.equalsIgnoreCase("false")) {
                req.setAttribute("error", "Operation Failed!");
            }
        }

        List<SeekerJobDTO> seekerJobDTOS = SeekerService.getJobsList(userId);
        req.setAttribute("JobsList", seekerJobDTOS);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/seeker/yourjobs.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(CommonUtil.getRedirectURL(""));
    }
}
