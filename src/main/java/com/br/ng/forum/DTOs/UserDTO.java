package com.br.ng.forum.DTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    // @NotBlank
    // @Email
    private String email;

    // @NotBlank
    private String name;

    @JsonIgnore
    @JsonProperty(value = "password")
    private String password;

    @JsonIgnore
    @JsonProperty(value = "password")
    private String roles;
    private boolean active;
}
