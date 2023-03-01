package com.spring.security.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class UserModelDto {
    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
