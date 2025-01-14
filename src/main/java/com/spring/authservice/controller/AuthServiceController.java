package com.spring.authservice.controller;

import com.spring.authservice.dto.UserRegisterRequest;
import com.spring.authservice.dto.UserRegisterResponse;
import com.spring.authservice.service.AuthService;
import com.spring.authservice.util.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    BaseResponse<String> greetUser(){
        BaseResponse<String> response = new BaseResponse<>();
        String responseMessage = "Hi! Welcome to AuthService";
            return response.setSuccess(HttpStatus.OK.value(),responseMessage, null);
    }

    @PostMapping("/register")
    BaseResponse<String> userRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        BaseResponse<String> response = new BaseResponse<>();
        UserRegisterResponse userRegisterResponse = authService.userRegister(userRegisterRequest);
        if (userRegisterResponse.isRegistered()){
            return response.setSuccess(HttpStatus.OK.value(), userRegisterResponse.message(), null);
        }else{
            return response.setFailure(HttpStatus.OK.value(), userRegisterResponse.message(), null);
        }
    }
}