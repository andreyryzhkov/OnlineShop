package com.aryzhkov.onlineshop.web.servlet.auth;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.service.SecurityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Auth {
    private SecurityService securityService;

    public Auth(SecurityService securityService) {
        this.securityService = securityService;
    }

    public Session getSession(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    Session session = securityService.getSession(cookie.getValue());
                    return session;
                }
                break;
            }
        }
        return null;
    }
}