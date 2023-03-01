package com.spring.security.services;

import com.spring.security.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserModelService service;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserModel> userModel = service.findByName(userName);
        if(userModel.isPresent()){
            return userModel.get();
        }

        throw new RuntimeException("Usuário não encontrado userdetails!");
    }
}
