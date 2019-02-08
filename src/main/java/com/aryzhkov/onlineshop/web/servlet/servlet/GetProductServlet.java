package com.aryzhkov.onlineshop.web.servlet.servlet;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.service.OnlineShopService;
import com.aryzhkov.onlineshop.web.servlet.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProductServlet extends HttpServlet {

    private OnlineShopService onlineShopService;

    public GetProductServlet(OnlineShopService onlineShopService) {
        this.onlineShopService = onlineShopService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
     //   List<Product> products = onlineShopService.getAllProduct();
        PageGenerator pageGenerator = PageGenerator.instance();

        Map<String, Object> pageVariables = new HashMap<>();
      //  pageVariables.put("products", products);

        String page = pageGenerator.getPage("allproduct.html", pageVariables);
        response.getWriter().write(page);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}