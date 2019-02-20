package com.aryzhkov.onlineshop.web.auth;

import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;

public class AdminRoleFilter extends AbstractRoleFilter {

    public AdminRoleFilter(SecurityService securityService) {
        super(securityService);
    }

    @Override
    protected boolean isValidRole(UserType userType) {
        return userType == UserType.ADMIN;
    }
}
