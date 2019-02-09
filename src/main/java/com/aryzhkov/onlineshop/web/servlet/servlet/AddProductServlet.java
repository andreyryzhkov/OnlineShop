package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.web.servlet.auth.Authentication;
import com.aryzhkov.onlineshop.web.servlet.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AddProductServlet extends HttpServlet {

    private ProductService productService;
    private List<String> users;

    public AddProductServlet(ProductService productService, List<String> users) {
        this.productService = productService;
        this.users = users;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        String token = Authentication.getLoginAuthentication(cookies, users);
/**
        if (token == null) {
            response.sendRedirect("/login");
        } else {
            boolean isAuthorization = Authentication.isAuthorization(productService, token);
            if (isAuthorization) {
                PageGenerator pageGenerator = PageGenerator.instance();
                String page = pageGenerator.getPage("addproduct.html", new HashMap<>());
                response.getWriter().write(page);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Access denied");
            }
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("productname");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(name, price);
     //   onlineShopService.insertProduct(product);

        response.sendRedirect("/products");
    }
}