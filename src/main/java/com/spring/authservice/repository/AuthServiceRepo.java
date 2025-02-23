package com.spring.authservice.repository;

import com.spring.authservice.model.UserLoginInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;

public interface AuthServiceRepo extends JpaRepository<UserLoginInfo, String> {
    UserLoginInfo findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USER_LOGIN_INFO SET LAST_LOGIN_TIME = :lastLoginTime WHERE USER_ID = :userId", nativeQuery = true)
    int updateLoginTime(@Param("userId") String userId, @Param("lastLoginTime") OffsetDateTime lastLoginTime);
}