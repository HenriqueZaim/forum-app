package com.br.ng.forum.DTOs.user.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Campo 'nome' não pode ser vazio")
    private String name;

    @NotBlank(message = "Campo 'e-mail' não pode ser vazio")
    private String email;

    @NotBlank(message = "Campo 'senha' não pode ser vazio")
    private String password;

    private String role;
    private boolean active;

    public UserRequestDTO(){
        this.active = true;
        this.role = "USER";
    }
}
