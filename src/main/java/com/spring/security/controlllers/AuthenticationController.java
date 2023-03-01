package com.spring.security.controlllers;

import com.spring.security.dtos.LoginDto;
import com.spring.security.dtos.TokenDto;
import com.spring.security.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid LoginDto loginDto){
        UsernamePasswordAuthenticationToken loginDatas = loginDto.converter();
         try{
            Authentication authentication = authenticationManager.authenticate(loginDatas);
            String token = tokenService.generateToken(authentication);
            return new ResponseEntity(new TokenDto(token, "Bearer"), HttpStatus.OK);
        }catch (Exception ex){
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
         }
    }
}
