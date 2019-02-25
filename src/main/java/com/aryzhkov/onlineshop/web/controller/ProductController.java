package com.aryzhkov.onlineshop.web.controller;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public void getProducts(HttpServletResponse httpServletResponse) throws IOException {
        List<Product> products = productService.getAllProduct();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", products);

        httpServletResponse.getWriter().println(PageGenerator.instance().getPage("allproduct.html", pageVariables));

        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
