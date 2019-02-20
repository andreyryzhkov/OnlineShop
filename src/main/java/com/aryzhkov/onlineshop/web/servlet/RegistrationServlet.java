package com.aryzhkov.onlineshop.web.servlet;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {

    private SecurityService securityService;

    public RegistrationServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        resp.getWriter().println(PageGenerator.instance().getPage("registration.html", pageVariables));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String usertype = req.getParameter("userrole");

        User user = securityService.newUser(login, password, UserType.getByName(usertype));
        Session session = securityService.getSession(user);

        if (session != null) {
            Cookie cookie = new Cookie("token", session.getToken());
            resp.addCookie(cookie);
            resp.sendRedirect("/products");
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
