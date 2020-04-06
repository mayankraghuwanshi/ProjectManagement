package com.mayank.ProjectManagement.Controllers;

import com.mayank.ProjectManagement.Entities.User;
import com.mayank.ProjectManagement.Services.ErrorResponse;
import com.mayank.ProjectManagement.Services.UserService;
import com.mayank.ProjectManagement.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    ErrorResponse errorResponse;
    @Autowired
    UserValidator userValidator;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid User user , BindingResult result){
        userValidator.validate(user , result);
        if(result.hasErrors()) return errorResponse.mapErrorToResponse(result);
        User createdUser = userService.createUser(user);
        return new ResponseEntity(createdUser, HttpStatus.OK);
    }
}
