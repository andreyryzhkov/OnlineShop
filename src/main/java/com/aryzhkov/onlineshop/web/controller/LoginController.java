package com.aryzhkov.onlineshop.web.controller;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @GetMapping(path = "/login")
    public void loginPage(HttpServletResponse httpServletResponse) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        httpServletResponse.getWriter().println(PageGenerator.instance().getPage("login.html", pageVariables));
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

    @PostMapping(path = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    @GetMapping(path = "/logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    Session session = securityService.getSession(cookie.getValue());
                    securityService.removeSession(session);
                }
            }
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.sendRedirect("/products");
    }

    @GetMapping(path = "/registration")
    public void registrationPage(HttpServletResponse httpServletResponse) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        httpServletResponse.getWriter().println(PageGenerator.instance().getPage("registration.html", pageVariables));
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

    @PostMapping(path = "/registration")
    public void registration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        String usertype = httpServletRequest.getParameter("userrole");

        User user = securityService.newUser(login, password, UserType.getByName(usertype));
        Session session = securityService.getSession(user);

        if (session != null) {
            Cookie cookie = new Cookie("token", session.getToken());
            httpServletResponse.addCookie(cookie);
            httpServletResponse.sendRedirect("/products");
        } else {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}