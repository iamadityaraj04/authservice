package com.spring.authservice.service.impl;

import com.spring.authservice.dto.UserInfo;
import com.spring.authservice.dto.UserLoginRequest;
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

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            String userId = String.valueOf(UUID.randomUUID());
            userLoginInfo.setUserId(userId);
            userLoginInfo.setEmail(userRegisterRequest.email().toLowerCase());
            userLoginInfo.setPassword(userRegisterRequest.password());
            userLoginInfo.setOldPassword(userRegisterRequest.password());
            userLoginInfo.setCreatedOn(OffsetDateTime.now());
            userLoginInfo.setModifiedOn(OffsetDateTime.now());
            userLoginInfo.setUserStatus(UserStatus.ACTIVE);

            authServiceRepo.save(userLoginInfo);
            log.info("User registered with userId: {} and email: {}", userId, userRegisterRequest.email());
            return response.setSuccess(
                    HttpStatus.CREATED.value(),
                    "User Registered Successfully",
                    null
            );
        }
        catch (Exception e){
            log.error("Failed Registration: {}",e.getMessage());
            return response.setFailure(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "User Registration Failed",
                    null
            );
        }
    }

    @Override
    public BaseResponse<List<UserInfo>> getAllUsers(){
        BaseResponse<List<UserInfo>> response = new BaseResponse<>();
        List<UserLoginInfo> userLoginInfoList = authServiceRepo.findAll();

        if(userLoginInfoList.isEmpty()){
            return response.setSuccess(
                    HttpStatus.NOT_FOUND.value(),
                    "No records found",
                    List.of()
            );
        }

        List<UserInfo> userInfoList = new ArrayList<>();
        for (UserLoginInfo userLoginInfo: userLoginInfoList){
            UserInfo userInfo = new UserInfo(
                    userLoginInfo.getUserId(),
                    userLoginInfo.getEmail(),
                    userLoginInfo.getCreatedOn(),
                    userLoginInfo.getLastLoginTime(),
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

    @Override
    public BaseResponse<Object> userLogin(UserLoginRequest userLoginRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        UserLoginInfo existingUser = authServiceRepo.findByEmail(userLoginRequest.email().toLowerCase());

        if(existingUser == null){
            return response.setSuccess(
                    HttpStatus.NOT_FOUND.value(),
                    "Account Not Found! Please Register",
                    null
            );
        }
        if(!userLoginRequest.password().equals(existingUser.getPassword())){
            return response.setSuccess(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Wrong Credentials! Please try again.",
                    null
            );
        }else{
            try{
                int rowAffected = authServiceRepo.updateLoginTime(existingUser.getUserId(), OffsetDateTime.now());
                if(rowAffected == 1){
                    log.info("Login time updated for user with ID: {}", existingUser.getUserId());
                }
            } catch (Exception e) {
                log.error("Error occurred while updating user login time for user ID: {}", existingUser.getUserId());
                throw new RuntimeException(e.getMessage());
            }


            return response.setSuccess(
                    HttpStatus.OK.value(),
                    "Congratulations! Logged In Successfully.",
                    null
            );
        }
    }
}
