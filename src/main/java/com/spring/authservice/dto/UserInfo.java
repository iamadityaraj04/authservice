package com.spring.authservice.dto;

import com.spring.authservice.enums.UserStatus;
import java.time.LocalDate;

public record UserInfo(
        Long userId,
        String email,
        LocalDate createdOn,
        UserStatus status
) {
}
