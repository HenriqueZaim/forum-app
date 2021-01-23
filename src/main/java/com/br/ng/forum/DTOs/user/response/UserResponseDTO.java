package com.br.ng.forum.DTOs.user.response;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.br.ng.forum.DTOs.post.response.PostResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO implements Serializable{ 

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String email;
    private String name;
    
    private List<PostResponseDTO> posts;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
