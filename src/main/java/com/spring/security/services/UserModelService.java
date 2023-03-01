package com.spring.security.services;

import com.spring.security.Cript;
import com.spring.security.dtos.UserModelDto;
import com.spring.security.models.UserModel;
import com.spring.security.repositories.UserModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserModelService {

    @Autowired
    UserModelRepository userModelRepository;

    public UserModelDto save(UserModelDto userModelDto){
        UserModel userModel = new UserModel(userModelDto.getName(), Cript.bCryptPasswordEncoder().encode(userModelDto.getPassword()));
        UserModel userModelResponse = userModelRepository.save(userModel);
        UserModelDto userModelDtoResponse = new UserModelDto();
        BeanUtils.copyProperties(userModelResponse, userModelDtoResponse);
        return userModelDtoResponse;
    }

    public Optional<UserModel> findByName(String name){
        return userModelRepository.findByName(name);
    }

    public List<UserModel> findAll() {
        return userModelRepository.findAll();
    }

    public UserModel findById(UUID userId) {
        return userModelRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }
}
