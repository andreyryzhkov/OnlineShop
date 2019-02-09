package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.web.servlet.auth.Authentication;

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
        String token = Authentication.getLoginAuthentication(cookies, users);
        if (token != null) {
            users.remove(token);
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/products");
    }
}
