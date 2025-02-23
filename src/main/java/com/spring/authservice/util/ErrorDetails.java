package com.spring.authservice.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String errorCode;
    private String errorDetails;
}