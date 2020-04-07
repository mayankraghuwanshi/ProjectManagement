package com.mayank.ProjectManagement.Payloads;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "please enter email")
    private String email;
    @NotBlank(message = "please enter password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
