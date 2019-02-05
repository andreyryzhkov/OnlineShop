package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.service.OnlineShopService;
import com.aryzhkov.onlineshop.web.servlet.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProductServlet extends HttpServlet {

    private OnlineShopService onlineShopService;

    public EditProductServlet(OnlineShopService onlineShopService) {
        this.onlineShopService = onlineShopService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = onlineShopService.getProductById(id);

        pageVariables.put("product", product);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("editproduct.html", pageVariables);
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("productname");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(id, productName, price);
        onlineShopService.updateProduct(product);

        response.sendRedirect("/products");
    }
}