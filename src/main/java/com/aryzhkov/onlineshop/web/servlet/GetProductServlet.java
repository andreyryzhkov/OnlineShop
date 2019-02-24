package com.aryzhkov.onlineshop.web.servlet;

import com.aryzhkov.onlineshop.ServiceLocator;
import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.service.UserService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProductServlet extends HttpServlet {

    private ProductService productService = ServiceLocator.getService(ProductService.class);

    public GetProductServlet() {

    }

    public GetProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAllProduct();
        PageGenerator pageGenerator = PageGenerator.instance();

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", products);

        //       FileInputStream fileInputStream = new FileInputStream("allproduct.html");

       // ServletContext servletContext = getServletContext();
       // String contextPath = servletContext.getRealPath("WEB-INF/classes/templates/allproduct.html");

        //  String uri = request.getRequestURI();
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("templates/allproduct.html");

      //  String page = pageGenerator.getPage(inputStream, pageVariables);

        String page = pageGenerator.getPage("allproduct.html", pageVariables);
        response.getWriter().write(page);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}