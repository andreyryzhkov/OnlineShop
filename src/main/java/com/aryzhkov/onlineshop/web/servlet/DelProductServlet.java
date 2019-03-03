/**
package com.aryzhkov.onlineshop.web.servlet;

import com.aryzhkov.onlineshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelProductServlet extends HttpServlet {

    private ProductService productService;

    public DelProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProduct(id);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/products");
    }
}
 */