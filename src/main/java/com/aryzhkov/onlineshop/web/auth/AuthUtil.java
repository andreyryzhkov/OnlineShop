package com.aryzhkov.onlineshop.web.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AuthUtil {

    public static String getSessionToken(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
                break;
            }
        }
        return null;
    }
}