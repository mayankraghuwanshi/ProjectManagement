package com.mayank.ProjectManagement.Security;

import com.mayank.ProjectManagement.Entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.mayank.ProjectManagement.Security.SecurityConstants.EXPIRE_TIME;
import static com.mayank.ProjectManagement.Security.SecurityConstants.SECRET;

@Component
public class JWTTokenProvider {

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date nowDate = new Date(System.currentTimeMillis());
        Date expDate = new Date(EXPIRE_TIME+System.currentTimeMillis());
        String userId = Long.toString(user.getId());
        Map<String,Object> claims = new HashMap<>();
        claims.put("id" , userId);
        claims.put("email" , user.getEmail());
        claims.put("password" , user.getPassword());
        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }
}
