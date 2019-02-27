package com.aryzhkov.onlineshop.web.auth;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractRoleFilter implements Filter {

    private SecurityService securityService;

    public AbstractRoleFilter() {

    }

    public AbstractRoleFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (securityService == null) {
            ServletContext servletContext = filterConfig.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            securityService = webApplicationContext.getBean(SecurityService.class);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String sessionToken = AuthUtil.getSessionToken(httpServletRequest);
        Session session = securityService.getSession(sessionToken);

        if (session == null) {
            httpServletResponse.sendRedirect("/login");
        } else {
            UserType userType = session.getUser().getUserType();
            if (isValidRole(userType)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Cannot access for add/edit product");
            }
        }
    }

    protected abstract boolean isValidRole(UserType userType);

    @Override
    public void destroy() {

    }
}