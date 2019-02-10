package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.service.SecurityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private SecurityService securityService;

    public LogoutServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    Session session = securityService.getByToken(cookie.getValue());
                    securityService.removeSession(session);
                }
            }
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/products");
    }
}