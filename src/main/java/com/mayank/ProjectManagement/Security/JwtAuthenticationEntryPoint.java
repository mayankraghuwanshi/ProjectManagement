package com.mayank.ProjectManagement.Security;

import com.google.gson.Gson;
import com.mayank.ProjectManagement.Exceptions.InvalidUserResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {




    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        InvalidUserResponse invalidUserResponse = new InvalidUserResponse();
        String jsonLoginResponse = new Gson().toJson(invalidUserResponse);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().println(jsonLoginResponse);
    }
}
