package com.aryzhkov.onlineshop.web.controller;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public String getProducts(HttpServletResponse httpServletResponse, ModelMap modelMap) throws IOException {
        modelMap.addAttribute("products", productService.getAllProduct());
        return "allproduct";
    }

    @GetMapping(path = "/product/add")
    public String addProductPage(HttpServletResponse httpServletResponse) {
        return "addproduct";
    }

    @PostMapping(path = "/product/add")
    public String addProduct(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String name = httpServletRequest.getParameter("productname");
        double price = Double.parseDouble(httpServletRequest.getParameter("price"));
        LocalDate dateMaking = LocalDate.parse(httpServletRequest.getParameter("datemaking"));

        Product product = new Product(name, price, dateMaking);
        productService.insertProduct(product);

        return "redirect:/products";
    }

    @PostMapping(path = "/product/delete")
    public String deleteProduct(@RequestParam int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping(path = "/product/edit")
    public String editProductPage(@RequestParam int id, ModelMap modelMap) {
        modelMap.addAttribute("product", productService.getProductById(id));
        return "editproduct";
    }

    @PostMapping(path = "/product/edit")
    public String editProduct(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        String productName = httpServletRequest.getParameter("productname");
        double price = Double.parseDouble(httpServletRequest.getParameter("price"));
        LocalDate dateMaking = LocalDate.parse(httpServletRequest.getParameter("datemaking"));

        Product product = new Product(id, productName, price, dateMaking);
        productService.updateProduct(product);
        return "redirect:/products";
    }
}