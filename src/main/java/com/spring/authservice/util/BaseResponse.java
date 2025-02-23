package com.spring.authservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private ErrorDetails errors;

    public BaseResponse<T> setSuccess(Integer status, String message, T data){
        return new BaseResponse<>(status,message,data,errors);
    }

    public BaseResponse<T> setFailure(Integer status, String message, T data){
        return new BaseResponse<>(status, message, data, errors);
    }
}