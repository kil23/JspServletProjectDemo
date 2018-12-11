package org.care.presentation.visitor;

import org.care.dto.SeekerRegistrationFormDTO;
import org.care.dto.SitterRegistrationFormDTO;
import org.care.model.Member;
import org.care.model.Seeker;
import org.care.model.Sitter;
import org.care.service.SeekerService;
import org.care.service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MemberRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/jsp/visitor/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String phoneNo = req.getParameter("phoneno");
        String emailId = req.getParameter("emailid");
        String password = req.getParameter("password");
        Member.MemberType memberType = (Member.MemberType) session.getAttribute("MemberType");
        String address = req.getParameter("address");
        String pincode = req.getParameter("pincode");

        if (memberType == Member.MemberType.SITTER) {

            String experience = req.getParameter("experience");
            SitterRegistrationFormDTO sFormData = new SitterRegistrationFormDTO(firstName, lastName, phoneNo, emailId,
                    password, memberType.toString(), address, pincode, experience);

            if (sFormData.validate()) {
                SitterService.register(sFormData);
                req.setAttribute("msg", "Registered Successfully!");
                req.getRequestDispatcher("/WEB-INF/jsp/visitor/login.jsp").forward(req, resp);

            } else {
                req.setAttribute("errors", sFormData.getErrors());
                req.setAttribute("formData", sFormData);
                req.getRequestDispatcher("/WEB-INF/jsp/visitor/register.jsp").forward(req, resp);
            }

        } else if (memberType == Member.MemberType.SEEKER) {
            String totalChildren = req.getParameter("totalchildren");
            String spouseName = req.getParameter("spousename");
            SeekerRegistrationFormDTO sFormData = new SeekerRegistrationFormDTO(firstName, lastName, phoneNo, emailId,
                    password, memberType.toString(), address, pincode, totalChildren, spouseName);

            if (sFormData.validate()) {
                SeekerService.register(sFormData);
                req.setAttribute("msg", "Registered Successfully!");
                req.getRequestDispatcher("/WEB-INF/jsp/visitor/login.jsp").forward(req, resp);

            } else {
                req.setAttribute("errors", sFormData.getErrors());
                req.setAttribute("formData", sFormData);
                req.getRequestDispatcher("/WEB-INF/jsp/visitor/register.jsp").forward(req, resp);
            }
        }

    }
}
