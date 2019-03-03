package com.aryzhkov.onlineshop.web.controller;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    private final SecurityService securityService;

    @Autowired
    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping(path = "/login")
    public String loginPage(HttpServletResponse httpServletResponse) throws IOException {
        return "login";
    }

    @PostMapping(path = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = securityService.login(login, password);
        Session session = securityService.getSession(user);

        if (session != null) {
            Cookie cookie = new Cookie("token", session.getToken());
            response.addCookie(cookie);
            return "redirect:/products";
        } else {
            return "login";
        }
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    Session session = securityService.getSession(cookie.getValue());
                    securityService.removeSession(session);
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping(path = "/registration")
    public String registrationPage(HttpServletResponse httpServletResponse) throws IOException {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        String usertype = httpServletRequest.getParameter("userrole");

        User user = securityService.newUser(login, password, UserType.getByName(usertype));
        Session session = securityService.getSession(user);
// TODO: add isUserCreated
        if (session != null) {
            Cookie cookie = new Cookie("token", session.getToken());
            httpServletResponse.addCookie(cookie);
            return "allproduct";
        } else {
            return "login";
        }
    }
}