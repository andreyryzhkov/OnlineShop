package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.service.OnlineShopService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DelProductServlet extends HttpServlet {

    private OnlineShopService onlineShopService;
    private List<String> users;

    public DelProductServlet(OnlineShopService onlineShopService, List<String> users) {
        this.onlineShopService = onlineShopService;
        this.users = users;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String token = Authentication.getLoginAuthentication(cookies, users);
        boolean isAuthorization = Authentication.isAuthorization(onlineShopService, token);

        if (token == null) {
            resp.sendRedirect("/login");
        } else {
            if (isAuthorization) {
                int id = Integer.parseInt(req.getParameter("id"));
                onlineShopService.deleteProduct(id);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/products");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Access denied");
            }
        }
    }
}
