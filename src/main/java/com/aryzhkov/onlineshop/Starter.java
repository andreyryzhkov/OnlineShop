package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.jdbc.JdbcProductDao;
import com.aryzhkov.onlineshop.dao.jdbc.JdbcUserDao;
import com.aryzhkov.onlineshop.dao.jdbc.connection.JdbcConnection;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.service.UserService;
import com.aryzhkov.onlineshop.web.servlet.auth.AuthFilter;
import com.aryzhkov.onlineshop.web.servlet.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

public class Starter {
    private static final String PROPERTIES_PATH = "db.properties";

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fileInputStream);
        }

        JdbcConnection jdbcConnection = new JdbcConnection();
        jdbcConnection.setProperties(properties);

        JdbcProductDao jdbcProductDao = new JdbcProductDao(jdbcConnection);
        ProductService productService = new ProductService(jdbcProductDao);

        JdbcUserDao jdbcUserDao = new JdbcUserDao(jdbcConnection);
        UserService userService = new UserService(jdbcUserDao);

        List<String> tokens = new ArrayList<>();

        GetProductServlet getProductServlet = new GetProductServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService);
        EditProductServlet editProductServlet = new EditProductServlet(productService);
        DelProductServlet delProductServlet = new DelProductServlet(productService);

        LoginServlet loginServlet = new LoginServlet(userService, tokens);
        LogoutServlet logoutServlet = new LogoutServlet(tokens);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(getProductServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");
        context.addServlet(new ServletHolder(delProductServlet), "/product/delete");
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");

        context.addFilter(new FilterHolder(new AuthFilter(tokens)), "/product/*", EnumSet.of(DispatcherType.REQUEST,
                DispatcherType.FORWARD));

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
