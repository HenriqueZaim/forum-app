package com.br.ng.forum.DTOs.user;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.br.ng.forum.DTOs.topic.TopicResponseDTO;
import com.br.ng.forum.models.Answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO implements Serializable{ 

    private static final long serialVersionUID = 1L;
    
    private UUID id;
    private String email;
    private String name;
    private String image;
    
    private List<TopicResponseDTO> topics;
    private List<Answer> answers;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;

}
