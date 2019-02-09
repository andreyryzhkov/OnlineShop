package com.aryzhkov.onlineshop.web.servlet.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogoutServlet extends HttpServlet {
    private List<String> tokens;

    public LogoutServlet(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    tokens.remove(cookie.getValue());
                }
            }
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/products");
    }
}