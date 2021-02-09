package com.br.ng.forum.DTOs.user.response;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSimpleResponseDTO implements Serializable{ 

    private static final long serialVersionUID = 1L;
    
    private UUID id;
    private String email;
    private String name;
}
