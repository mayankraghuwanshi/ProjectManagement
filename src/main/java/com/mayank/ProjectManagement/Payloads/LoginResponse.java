package com.mayank.ProjectManagement.Payloads;

public class LoginResponse {
    private boolean successp;
    private String token;

    public LoginResponse(boolean successp, String token) {
        this.successp = successp;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccessp() {
        return successp;
    }

    public void setSuccessp(boolean successp) {
        this.successp = successp;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "successp=" + successp +
                '}';
    }
}
