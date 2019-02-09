package com.aryzhkov.onlineshop.web.servlet.auth;

import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.web.servlet.templater.PageGenerator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AuthFilter /**implements Filter*/ {

    private List<User> users;

    public AuthFilter(List<User> users) {
        this.users = users;
    }

  //  @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

      //  String token = Authentication.getLoginAuthentication(cookies, users);
/**
        if (token == null) {
            httpServletResponse.sendRedirect("/login");
        } else {
            boolean isAuthorization = Authentication.isAuthorization(onlineShopService, token);
            if (!isAuthorization) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Access denied");
            }
        }

        if (isTokenValid(httpServletRequest, tokens)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
    */
    }
}
