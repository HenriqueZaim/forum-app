package com.br.ng.forum.DTOs.post.response;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.br.ng.forum.DTOs.user.response.UserResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private String text;
    private UserResponseDTO user;
    private PostSimpleResponseDTO parentPost;
    private List<PostChildrenResponseDTO> answers;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
