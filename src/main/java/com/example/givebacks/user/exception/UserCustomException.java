package com.example.givebacks.user.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice

public class UserCustomException extends RuntimeException {



    public UserCustomException() {

    }

    public UserCustomException(UserErrorCode userErrorCode) {
        super(userErrorCode.getMessage());
    }
}
