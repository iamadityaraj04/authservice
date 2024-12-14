package com.spring.authservice.dto;

public record UserRegisterRequest(
        String email,
        String password
) {
}
