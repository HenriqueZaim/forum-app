package com.br.ng.forum.DTOs.post.request;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.br.ng.forum.DTOs.post.response.PostResponseDTO;
import com.br.ng.forum.DTOs.post.response.PostSimpleResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private String text;
    private PostSimpleResponseDTO parentPost;
    private List<PostResponseDTO> answers;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
