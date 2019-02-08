package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.jdbc.connection.JdbcConnection;
import com.aryzhkov.onlineshop.service.OnlineShopService;
import com.aryzhkov.onlineshop.web.servlet.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.FileInputStream;
import java.util.ArrayList;
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


/**
        OnlineShopDao onlineShopDao = new OnlineShopDao();
        OnlineShopService onlineShopService = new OnlineShopService(onlineShopDao);
        List<String> users = new ArrayList<>();

        GetProductServlet getProductServlet = new GetProductServlet(onlineShopService);
        AddProductServlet addProductServlet = new AddProductServlet(onlineShopService, users);
        EditProductServlet editProductServlet = new EditProductServlet(onlineShopService, users);
        DelProductServlet delProductServlet = new DelProductServlet(onlineShopService, users);
        LoginServlet loginServlet = new LoginServlet(onlineShopService, users);
        LogoutServlet logoutServlet = new LogoutServlet(users);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(getProductServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");
        context.addServlet(new ServletHolder(delProductServlet), "/product/delete");
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
 */
    }
}
