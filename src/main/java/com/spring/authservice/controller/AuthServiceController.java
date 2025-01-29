package com.spring.authservice.controller;

import com.spring.authservice.dto.UserInfo;
import com.spring.authservice.dto.UserRegisterRequest;
import com.spring.authservice.service.AuthService;
import com.spring.authservice.util.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@Tag(name = "AuthService")
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

    @Operation(summary = "get all users")
    @GetMapping("/get")
    BaseResponse<List<UserInfo>> getAllUser(){
        return authService.getAllUsers();
    }

    @Operation(summary = "register user")
    @PostMapping("/register")
    BaseResponse<Object> userRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        return authService.userRegister(userRegisterRequest);
    }
}