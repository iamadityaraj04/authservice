package com.spring.authservice.repository;

import com.spring.authservice.model.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthServiceRepo extends JpaRepository<UserLoginInfo, String> {
    UserLoginInfo findByEmail(String email);
}