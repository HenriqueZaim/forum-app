package com.br.ng.forum.DTOs.answer;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.br.ng.forum.DTOs.topic.TopicSimpleResponseDTO;
import com.br.ng.forum.DTOs.user.UserSimpleResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerResponseDTO {
    
    private UUID id;
    private String text;
    private Long upvotes;
    private UserSimpleResponseDTO user;
    private TopicSimpleResponseDTO topic;
    private AnswerSimpleResponseDTO parentAnswer;
    private List<AnswerSimpleResponseDTO> answers;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
