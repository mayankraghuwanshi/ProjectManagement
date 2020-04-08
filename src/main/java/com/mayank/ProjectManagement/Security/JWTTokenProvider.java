package com.mayank.ProjectManagement.Security;

import com.mayank.ProjectManagement.Entities.User;
import io.jsonwebtoken.*;
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

    public boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException ex){
            System.out.println("Signature ex");
        }catch (MalformedJwtException ex){
            System.out.println("Malformed ex");
        }catch (ExpiredJwtException ex){
            System.out.println("Expire ex");
        }catch (IllegalArgumentException ex){
            System.out.println("Illegal ex");
        }
        return false;
    }


    public Long getUserId(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);

    }

}
