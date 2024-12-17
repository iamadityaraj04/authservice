package com.spring.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
        @NotBlank(message = "Email can not be empty")
        @Email(message = "Invalid Email Id")
        String email,

        @NotBlank(message = "Password can not be empty")
        String password
) {
}
