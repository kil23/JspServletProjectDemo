package org.care.presentation.sitter;

import org.care.context.MyApplicationContext;
import org.care.dto.SitterAppliedJobDTO;
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

public class SitterHomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String success = req.getParameter("success");
        if(success != null) {
            if(success.equalsIgnoreCase("true")) {
                req.setAttribute("msg", "Operation Successful!");
            } else if (success.equalsIgnoreCase("false")) {
                req.setAttribute("error", "Operation Failed!");
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/sitter/home.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(CommonUtil.getRedirectURL("/sitter/home"));
    }
}
