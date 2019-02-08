package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.service.OnlineShopService;

import javax.servlet.http.Cookie;
import java.util.List;

public class Authentication {
    private static final String USER_TYPE_ADMIN = "ADMIN";
    private static final String USER_TYPE_GUEST = "GUEST";

    public static String getLoginAuthentication(Cookie[] cookies, List<String> users) {
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("UID") && (users.contains(cookie.getValue()))) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

    public static boolean isAuthorization(OnlineShopService onlineShopService, String token) {
        boolean isAuthorization = false;
     //   User user = onlineShopService.getUserById(token);
     //   if (USER_TYPE_ADMIN.equals(user.getUserType())) {
            isAuthorization = true;
     //   }
        return isAuthorization;
    }
}
