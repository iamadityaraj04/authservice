package com.spring.authservice.controller;

import com.spring.authservice.dto.UserRegisterRequest;
import com.spring.authservice.dto.UserRegisterResponse;
import com.spring.authservice.service.AuthService;
import com.spring.authservice.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class AuthServiceController {

    private final AuthService authService;

    @Autowired
    public AuthServiceController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping("/greet")
    String greetUser(){
        return "Hello! Welocome to the Auth Application";
    }

    @PostMapping("/register")
    BaseResponse<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        BaseResponse<String> response = new BaseResponse<>();
        UserRegisterResponse userRegisterResponse = authService.userRegister(userRegisterRequest);
        if (userRegisterResponse.isRegistered()){
            return response.setSuccess(userRegisterResponse.message(), null);
        }else{
            return response.setFailure(userRegisterResponse.message(), null);
        }
    }
}