package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.jdbc.ProductDao;
import com.aryzhkov.onlineshop.dao.jdbc.UserDao;
import com.aryzhkov.onlineshop.dao.jdbc.datasource.PGSDataSource;
import com.aryzhkov.onlineshop.service.ProductService;
import com.aryzhkov.onlineshop.service.SecurityService;
import com.aryzhkov.onlineshop.service.UserService;
import com.aryzhkov.onlineshop.web.servlet.auth.AuthFilter;
import com.aryzhkov.onlineshop.web.servlet.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.*;

public class Starter {
    private static final String PROPERTIES_PATH = "db.properties";

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fileInputStream);
        }

        PGSDataSource pgsDataSource = new PGSDataSource();
        pgsDataSource.setProperties(properties);
        DataSource dataSource = pgsDataSource.createDataSource();

        UserDao jdbcUserDao = new UserDao(dataSource);
        ProductDao jdbcProductDao = new ProductDao(dataSource);

        UserService userService = new UserService(jdbcUserDao);
        SecurityService securityService = new SecurityService(userService);
        ProductService productService = new ProductService(jdbcProductDao);

        GetProductServlet getProductServlet = new GetProductServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService);
        EditProductServlet editProductServlet = new EditProductServlet(productService);
        DelProductServlet delProductServlet = new DelProductServlet(productService);

        LoginServlet loginServlet = new LoginServlet(securityService);
        LogoutServlet logoutServlet = new LogoutServlet(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(getProductServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");
        context.addServlet(new ServletHolder(delProductServlet), "/product/delete");
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");

        context.addFilter(new FilterHolder(new AuthFilter(securityService)), "/product/*", EnumSet.of(DispatcherType.REQUEST,
                DispatcherType.FORWARD));

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
