package com.aryzhkov.onlineshop.web.servlet.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogoutServlet extends HttpServlet {
    private List<String> users;

    public LogoutServlet(List<String> users) {
        this.users = users;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && (users.contains(cookie.getValue()))) {
                    users.remove(cookie.getValue());
                }
            }
        }
        resp.sendRedirect("/products");
    }
}
