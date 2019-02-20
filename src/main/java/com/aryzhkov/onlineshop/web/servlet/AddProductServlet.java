package com.aryzhkov.onlineshop.web.servlet;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class AddProductServlet extends HttpServlet {

    private ProductService productService;

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("addproduct.html", new HashMap<>());
        response.getWriter().write(page);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("productname");
        double price = Double.parseDouble(request.getParameter("price"));
        LocalDate dateMaking = LocalDate.parse(request.getParameter("datemaking"));

        Product product = new Product(name, price, dateMaking);
        productService.insertProduct(product);

        response.sendRedirect("/products");
    }
}