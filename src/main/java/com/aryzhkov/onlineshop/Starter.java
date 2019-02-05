package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.OnlineShopDao;
import com.aryzhkov.onlineshop.service.OnlineShopService;
import com.aryzhkov.onlineshop.web.servlet.servlet.AddProductServlet;
import com.aryzhkov.onlineshop.web.servlet.servlet.DelProductServlet;
import com.aryzhkov.onlineshop.web.servlet.servlet.EditProductServlet;
import com.aryzhkov.onlineshop.web.servlet.servlet.GetProductServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {

    public static void main(String[] args) throws Exception {

        OnlineShopDao onlineShopDao = new OnlineShopDao();
        OnlineShopService onlineShopService = new OnlineShopService(onlineShopDao);

        GetProductServlet getProductServlet = new GetProductServlet(onlineShopService);
        AddProductServlet addProductServlet = new AddProductServlet(onlineShopService);
        EditProductServlet editProductServlet = new EditProductServlet(onlineShopService);
        DelProductServlet delProductServlet = new DelProductServlet(onlineShopService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(getProductServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");
        context.addServlet(new ServletHolder(delProductServlet), "/product/delete");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
