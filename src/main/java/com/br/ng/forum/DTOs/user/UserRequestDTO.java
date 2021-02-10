package com.br.ng.forum.DTOs.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Campo Nome não pode ser vazio")
    private String name;

    @NotBlank(message = "Campo E-mail não pode ser vazio")
    @Email(message = "Insira um E-mail válido")
    private String email;

    @NotBlank(message = "Campo Senha não pode ser vazio")
    private String password;

    private String role;

    public UserRequestDTO(){
        this.role = (role == null ? "USER" : role);
    }
}
