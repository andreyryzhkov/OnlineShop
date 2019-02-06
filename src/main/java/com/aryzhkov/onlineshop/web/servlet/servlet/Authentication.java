package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.service.OnlineShopService;

import javax.servlet.http.Cookie;
import java.util.List;

public class Authentication {

    public static String isAuthentication(Cookie[] cookies, List<String> users) {
        String login = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                login = cookie.getValue();
                if (cookie.getName().equals("user") && (users.contains(login))) {
                    return login;
                }
            }
        }
        return login;
    }

    public static boolean isAuthorization(OnlineShopService onlineShopService, String login) {
        boolean isAuthorization = false;
        User user = onlineShopService.getUser(login);
        if ("ADMIN".equals(user.getUserType())) {
            isAuthorization = true;
        }
        return isAuthorization;
    }

}
