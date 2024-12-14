package com.spring.authservice.service.impl;

import com.spring.authservice.dto.UserRegisterRequest;
import com.spring.authservice.dto.UserRegisterResponse;
import com.spring.authservice.model.UserLoginInfo;
import com.spring.authservice.repository.AuthServiceRepo;
import com.spring.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthServiceRepo authServiceRepo;

    @Autowired
    public AuthServiceImpl (AuthServiceRepo authServiceRepo){
        this.authServiceRepo = authServiceRepo;
    }

    @Override
    public UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest) {

        try {
            UserLoginInfo existingUser = authServiceRepo.findByEmail(userRegisterRequest.email());

            if(existingUser != null){
                return new UserRegisterResponse(false, "Already Exists! Please Login");
            }

            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setEmail(userRegisterRequest.email().toLowerCase());
            userLoginInfo.setPassword(userRegisterRequest.password());
            userLoginInfo.setOldPassword(userRegisterRequest.password());
            userLoginInfo.setCreatedOn(LocalDate.now());
            userLoginInfo.setModifiedOn(LocalDate.now());

            authServiceRepo.save(userLoginInfo);
            log.info("User Registered: {}",userLoginInfo);
            return new UserRegisterResponse(true, "Registered Successfully");
        }
        catch (Exception e){
            log.info("Failed Registration: {}",e.getMessage());
            return  new UserRegisterResponse(false, "Registration Failed");
        }

    }
}
