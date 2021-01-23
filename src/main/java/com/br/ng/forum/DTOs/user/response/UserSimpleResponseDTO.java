package com.br.ng.forum.DTOs.user.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSimpleResponseDTO implements Serializable{ 

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String email;
    private String name;
}
