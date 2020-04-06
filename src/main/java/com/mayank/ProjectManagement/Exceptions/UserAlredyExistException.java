package com.mayank.ProjectManagement.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlredyExistException extends RuntimeException {
    public UserAlredyExistException(String message){
        super(message);
    }
}
