package com.mayank.ProjectManagement.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException projectIdException , WebRequest request){
        ProjectIdExceptionResponse projectIdExceptionResponse = new ProjectIdExceptionResponse(projectIdException.getMessage());
        return new ResponseEntity(projectIdExceptionResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserAlredyExistException(UserAlredyExistException e , WebRequest request){
        UserAlredyExistResponse response = new UserAlredyExistResponse("Email alredy exist");
        return  new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }
}
