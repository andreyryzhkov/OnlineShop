package com.aryzhkov.onlineshop.web.controller;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.service.SecurityService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public void getProducts(HttpServletResponse httpServletResponse) throws IOException {
        List<Product> products = productService.getAllProduct();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", products);

        httpServletResponse.getWriter().println(PageGenerator.instance().getPage("allproduct.html", pageVariables));

        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public void loginPage(HttpServletResponse httpServletResponse) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        httpServletResponse.getWriter().println(PageGenerator.instance().getPage("login.html", pageVariables));
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = securityService.login(login, password);
        Session session = securityService.getSession(user);

        if (session != null) {
            Cookie cookie = new Cookie("token", session.getToken());
            response.addCookie(cookie);
            response.sendRedirect("/products");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
