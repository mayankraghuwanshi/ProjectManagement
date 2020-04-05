package com.mayank.ProjectManagement.Services;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;


@Service
public class ErrorResponse {
    public ResponseEntity<Map<String,String>> mapErrorToResponse(BindingResult result){
        Map<String,String> errorMapResponse = new HashMap<>();
        for(FieldError error : result.getFieldErrors()){
            System.out.println(error.getObjectName());
            errorMapResponse.put(error.getField(),error.getDefaultMessage());
        }
        return new ResponseEntity<Map<String,String>>(errorMapResponse, HttpStatus.OK);
    }
}
