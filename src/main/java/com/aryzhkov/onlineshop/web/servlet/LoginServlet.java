package com.aryzhkov.onlineshop.web.servlet;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.service.SecurityService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private SecurityService securityService;

    public LoginServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        response.getWriter().println(PageGenerator.instance().getPage("login.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = securityService.login(login, password);
        Session session = securityService.getSession(user);

        if (session != null) {
            Cookie cookie = new Cookie("token", session.getToken());
            response.addCookie(cookie);
            response.sendRedirect("/products");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}