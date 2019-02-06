package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.service.OnlineShopService;
import com.aryzhkov.onlineshop.web.servlet.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private OnlineShopService onlineShopService;
    private List<String> users;

    public LoginServlet(OnlineShopService onlineShopService, List<String> users) {
        this.onlineShopService = onlineShopService;
        this.users = users;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        //  String password =  req.getParameter("password"); TODO: need to check (Hash)

        User user = onlineShopService.getUserByName(login);
        String token = String.valueOf(user.getId());
        Cookie cookie = new Cookie("UID", token);
        resp.addCookie(cookie);
        users.add(token);

        resp.sendRedirect("/products");
    }
}