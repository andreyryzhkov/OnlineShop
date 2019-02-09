package com.aryzhkov.onlineshop.entity;

public enum UserType {

    ADMIN("ADMIN");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public static UserType getByName(String name) {
        UserType[] userRoles = UserType.values();
        for (UserType userType : userRoles) {
            if (userType.getName().equalsIgnoreCase(name)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("No role for name " + name);
    }

    public String getName() {
        return name;
    }
}
