package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.service.OnlineShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelProductServlet extends HttpServlet {

    private OnlineShopService onlineShopService;

    public DelProductServlet(OnlineShopService onlineShopService) {
        this.onlineShopService = onlineShopService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        onlineShopService.deleteProduct(id);

        resp.sendRedirect("/products");
    }
}
