package com.spring.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(
        @NotBlank @NotNull(message = "Email can not be empty.")
        @Email(message = "Invalid Email Id")
        String email,

        @NotBlank @NotNull(message = "Password can not be empty.")
        String password
) {
}
