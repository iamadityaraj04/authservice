package com.spring.authservice.dto;

public record UserRegisterResponse(
        Boolean isRegistered,
        String message
) {
}
