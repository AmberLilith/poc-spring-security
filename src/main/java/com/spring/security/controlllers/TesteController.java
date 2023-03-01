package com.spring.security.controlllers;

import com.spring.security.dtos.UserModelDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class TesteController {

    @GetMapping("/liberada")
    public ResponseEntity<String> liberada(){
        return new ResponseEntity<>("URI liberada", HttpStatus.OK);
    }

    @GetMapping("/protegida")
    public ResponseEntity<String> protegida(){
        return new ResponseEntity<>("URI protegida. Só acessar com autenticação!", HttpStatus.OK);
    }
}
