package com.spring.security.filters;

import com.spring.security.models.UserModel;
import com.spring.security.repositories.UserModelRepository;
import com.spring.security.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class AuthenticationViaTokenFilter extends OncePerRequestFilter {

    //@Autowired
    TokenService tokenService;

    //@Autowired
    UserModelRepository repository;

    public AuthenticationViaTokenFilter(TokenService tokenService, UserModelRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        Boolean tokenIsValid = tokenService.isValidToken(token);
        if(tokenIsValid){
            autenticateClient(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticateClient(String token){
        UUID userId = tokenService.getLoggedInUserId(token);
        UserModel userModel = repository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado autenticate!"));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userModel, null, userModel.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7);
    }


}
