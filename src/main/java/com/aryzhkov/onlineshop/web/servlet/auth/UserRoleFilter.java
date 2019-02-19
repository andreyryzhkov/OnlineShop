package com.aryzhkov.onlineshop.web.servlet.auth;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRoleFilter implements Filter {
    private SecurityService securityService;

    public UserRoleFilter(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Auth auth = new Auth(securityService);
        Session session = auth.getSession(httpServletRequest);

        if (session == null) {
            httpServletResponse.sendRedirect("/login");
        } else {
            UserType userType = session.getUser().getUserType();
            if (userType == UserType.ADMIN || userType == UserType.USER) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED_401, "Cannot access for products");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
