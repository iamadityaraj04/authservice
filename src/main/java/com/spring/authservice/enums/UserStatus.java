package com.spring.authservice.enums;

public enum UserStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    public final String status;

    UserStatus(String value) {
        status = value;
    }

    public static UserStatus getByValue(String status){
        return UserStatus.valueOf(status);
    }
}
