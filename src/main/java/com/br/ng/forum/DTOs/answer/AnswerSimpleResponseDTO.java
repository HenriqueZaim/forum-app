package com.br.ng.forum.DTOs.answer;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.br.ng.forum.DTOs.topic.TopicSimpleResponseDTO;
import com.br.ng.forum.DTOs.user.UserSimpleResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerSimpleResponseDTO {
    
    private UUID id;
    private String text;
    private Long upvotes;
    private UserSimpleResponseDTO user;
    private TopicSimpleResponseDTO topic;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
