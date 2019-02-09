package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.UserService;
import com.aryzhkov.onlineshop.web.servlet.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LoginServlet extends HttpServlet {

    private UserService userService;
    private List<String> tokens;

    public LoginServlet(UserService userService, List<String> tokens) {
        this.userService = userService;
        this.tokens = tokens;
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

        User user = userService.getUserByName(login);
        if (password.equals(user.getPassword())) {
            if (UserType.getByName(user.getUserType()) == UserType.ADMIN) {
                String token = UUID.randomUUID().toString();
                tokens.add(token);
                Cookie cookie = new Cookie("token", token);
                response.addCookie(cookie);
                response.sendRedirect("/products");
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid user/password");
        }
    }
}