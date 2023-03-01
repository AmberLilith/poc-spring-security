package com.spring.security.controlllers;

import com.spring.security.dtos.UserModelDto;
import com.spring.security.models.UserModel;
import com.spring.security.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserModelController {

    @Autowired
    UserModelService service;
    @PostMapping()
    public ResponseEntity<UserModelDto> save(@RequestBody @Valid UserModelDto userModelDto){
        return new ResponseEntity<>(service.save(userModelDto), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable UUID id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
