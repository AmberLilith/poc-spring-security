package com.spring.security.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@Getter
@Setter
public class LoginDto {

    private String login;

    private String pass;

    public LoginDto(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(this.login, this.pass);
    }
}
