package com.spring.authservice.service.impl;

import com.spring.authservice.dto.UserInfo;
import com.spring.authservice.dto.UserRegisterRequest;
import com.spring.authservice.enums.UserStatus;
import com.spring.authservice.model.UserLoginInfo;
import com.spring.authservice.repository.AuthServiceRepo;
import com.spring.authservice.service.AuthService;
import com.spring.authservice.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthServiceRepo authServiceRepo;

    @Autowired
    public AuthServiceImpl (AuthServiceRepo authServiceRepo){
        this.authServiceRepo = authServiceRepo;
    }

    @Override
    public BaseResponse<Object> userRegister(UserRegisterRequest userRegisterRequest) {

        BaseResponse<Object> response = new BaseResponse<>();
        try {
            UserLoginInfo existingUser = authServiceRepo.findByEmail(userRegisterRequest.email().toLowerCase());

            if(existingUser != null){
                return response.setSuccess(
                        HttpStatus.CONFLICT.value(),
                        "Account Already Exists! Please Login",
                        null
                );
            }

            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setEmail(userRegisterRequest.email().toLowerCase());
            userLoginInfo.setPassword(userRegisterRequest.password());
            userLoginInfo.setOldPassword(userRegisterRequest.password());
            userLoginInfo.setCreatedOn(LocalDate.now());
            userLoginInfo.setModifiedOn(LocalDate.now());
            userLoginInfo.setUserStatus(UserStatus.ACTIVE);

            authServiceRepo.save(userLoginInfo);
            return response.setSuccess(
                    HttpStatus.CREATED.value(),
                    "Registered Successfully",
                    null
            );
        }
        catch (Exception e){
            log.info("Failed Registration: {}",e.getMessage());
            return response.setFailure(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Registration Failed",
                    null
            );
        }
    }

    public BaseResponse<List<UserInfo>> getAllUsers(){
        BaseResponse<List<UserInfo>> response = new BaseResponse<>();
        List<UserLoginInfo> userLoginInfoList = authServiceRepo.findAll();

        if(userLoginInfoList.isEmpty()){
            return response.setSuccess(
                    HttpStatus.OK.value(),
                    "No records found",
                    List.of()
            );
        }

        List<UserInfo> userInfoList = new ArrayList<>();
        for (UserLoginInfo userLoginInfo: userLoginInfoList){
            UserInfo userInfo = new UserInfo(
                    userLoginInfo.getUserID(),
                    userLoginInfo.getEmail(),
                    userLoginInfo.getCreatedOn(),
                    userLoginInfo.getUserStatus()
                    );
            userInfoList.add(userInfo);
        }

        return response.setSuccess(
                HttpStatus.OK.value(),
                "Success",
                userInfoList
        );
    }
}
