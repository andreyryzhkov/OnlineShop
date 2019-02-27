package com.aryzhkov.onlineshop.web.auth;

import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;

public class UserRoleFilter extends AbstractRoleFilter {

    public UserRoleFilter() {
    }

    public UserRoleFilter(SecurityService securityService) {
        super(securityService);
    }

    @Override
    protected boolean isValidRole(UserType userType) {
        return userType == UserType.USER || userType == UserType.ADMIN;
    }
}
