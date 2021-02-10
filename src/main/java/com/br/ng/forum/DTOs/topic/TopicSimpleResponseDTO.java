package com.br.ng.forum.DTOs.topic;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.br.ng.forum.DTOs.user.UserSimpleResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicSimpleResponseDTO {
    
    private UUID id;
    private String title;
    private String text;
    private String image;
    private Long upvotes;
    private Long visualizations;
    private UserSimpleResponseDTO user;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
