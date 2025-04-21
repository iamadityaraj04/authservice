package com.spring.authservice.service;

import com.spring.authservice.dto.UserInfo;
import com.spring.authservice.dto.UserLoginRequest;
import com.spring.authservice.dto.UserRegisterRequest;
import com.spring.authservice.util.BaseResponse;

import java.util.List;

public interface AuthService {
    BaseResponse<Object> userRegister(UserRegisterRequest userRegisterRequest);

    BaseResponse<List<UserInfo>> getAllUsers();

    BaseResponse<Object> userLogin(UserLoginRequest userLoginRequest);
}