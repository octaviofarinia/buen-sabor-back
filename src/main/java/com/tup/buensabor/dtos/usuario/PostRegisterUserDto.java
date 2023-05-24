package com.tup.buensabor.dtos.usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostRegisterUserDto {
    private String userId;
    private String username;
}
