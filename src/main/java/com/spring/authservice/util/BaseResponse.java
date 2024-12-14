package com.spring.authservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String status;
    private String message;
    private T data;
    private ErrorDetails errors;

    public BaseResponse<T> setSuccess(String message, T data){
        return new BaseResponse<>("Success",message,data,errors);
    }

    public BaseResponse<T> setFailure(String message, T data){
        return new BaseResponse<>("Failure", message, data, errors);
    }
}