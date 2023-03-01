package com.spring.security.services;

import com.spring.security.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    public String generateToken(Authentication authentication){
        UserModel logedInUserModel = (UserModel) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong("900000"));
        return Jwts.builder()
                .setIssuer("Poc Spring Security")
                .setSubject(logedInUserModel.getUserId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60  *1000))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
    }

    public Boolean isValidToken(String token){
        try{
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public UUID getLoggedInUserId(String token){
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        return UUID.fromString(claims.getSubject());
    }


}
