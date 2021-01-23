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
    
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String role;
    private boolean active;

    public UserRequestDTO(){
        this.active = true;
        this.role = "USER";
    }
}
