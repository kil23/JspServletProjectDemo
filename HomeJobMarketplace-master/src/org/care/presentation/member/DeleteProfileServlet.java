package org.care.presentation.member;

import org.care.context.MyApplicationContext;
import org.care.service.MemberService;
import org.care.utils.CommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = MyApplicationContext.get().getMember().getId();
        boolean isDeleted = MemberService.deleteUser(userId);

        if (isDeleted) {
            session.invalidate();
            resp.sendRedirect(CommonUtil.getRedirectURL("?success=false"));
        } else {
            req.setAttribute("error", "Can't delete profile");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/member/profile");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(CommonUtil.getRedirectURL(""));
    }
}
