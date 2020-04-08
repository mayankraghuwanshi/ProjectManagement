package com.mayank.ProjectManagement.Security;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.mayank.ProjectManagement.Entities.User;
import com.mayank.ProjectManagement.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.mayank.ProjectManagement.Security.SecurityConstants.HEADER_STRING;
import static com.mayank.ProjectManagement.Security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getTokenFromRequest(httpServletRequest);
            if(StringUtils.hasText(token) && jwtTokenProvider.validate(token)) {
                Long userId = jwtTokenProvider.getUserId(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUserId(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Collections.emptyList()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch(Exception ex){
            logger.error("could not set user authentication in security context");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    public String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);
        System.out.println(bearerToken);
        if(StringUtils.hasText(bearerToken) &&  bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7,  bearerToken.length());
        }
        return null;
    }
}
