package com.aryzhkov.onlineshop.web.servlet.auth;

import com.aryzhkov.onlineshop.service.SecurityService;

import javax.servlet.*;
import java.io.IOException;

public class AdminRoleFilter implements Filter {
    private SecurityService securityService;

    public AdminRoleFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
