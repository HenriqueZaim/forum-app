package com.br.ng.forum.DTOs.post.response;

import java.io.Serializable;
import java.util.UUID;

import com.br.ng.forum.DTOs.user.response.UserSimpleResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSimpleResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private UUID id;
    private String title;
    private String text;
    private UserSimpleResponseDTO user;

}
