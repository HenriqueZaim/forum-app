package com.br.ng.forum.DTOs.response;

import java.util.List;

import com.br.ng.forum.models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {
    
    private Integer id;
    private String name;
    private String text;
    private Long followsUp;
    private UserResponseDTO user;
    private List<PostResponseDTO> answers;

}
