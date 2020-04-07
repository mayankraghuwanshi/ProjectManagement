package com.mayank.ProjectManagement.Controllers;

import com.mayank.ProjectManagement.Entities.User;
import com.mayank.ProjectManagement.Payloads.LoginRequest;
import com.mayank.ProjectManagement.Payloads.LoginResponse;
import com.mayank.ProjectManagement.Security.JWTTokenProvider;
import com.mayank.ProjectManagement.Services.ErrorResponse;
import com.mayank.ProjectManagement.Services.UserService;
import com.mayank.ProjectManagement.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.mayank.ProjectManagement.Security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ErrorResponse errorResponse;
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid User user , BindingResult result){
        userValidator.validate(user , result);
        if(result.hasErrors()) return errorResponse.mapErrorToResponse(result);
        User createdUser = userService.createUser(user);
        return new ResponseEntity(createdUser, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid LoginRequest loginRequest, BindingResult result){
        if(result.hasErrors()) return errorResponse.mapErrorToResponse(result);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX+jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponse(true , jwt));
    }
}
