package com.spring.authservice.service;

import com.spring.authservice.dto.UserRegisterRequest;
import com.spring.authservice.dto.UserRegisterResponse;

public interface AuthService {
    UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest);
}