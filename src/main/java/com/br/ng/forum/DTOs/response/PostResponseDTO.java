package com.br.ng.forum.DTOs.response;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String title;
    private String text;
    private UserResponseDTO user;
    private List<PostResponseDTO> answers;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
