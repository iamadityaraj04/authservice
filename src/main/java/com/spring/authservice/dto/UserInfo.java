package com.spring.authservice.dto;

import com.spring.authservice.enums.UserStatus;
import java.time.OffsetDateTime;

public record UserInfo(
        String userId,
        String email,
        OffsetDateTime createdOn,
        UserStatus status
) {
}
