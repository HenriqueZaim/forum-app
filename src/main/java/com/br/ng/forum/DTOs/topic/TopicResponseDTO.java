package com.br.ng.forum.DTOs.topic;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.br.ng.forum.DTOs.answer.AnswerResponseDTO;
import com.br.ng.forum.DTOs.user.UserSimpleResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private UUID id;
    private String title;
    private String text;
    private Long upvotes;
    private Long visualizations;
    private UserSimpleResponseDTO user;
    private List<AnswerResponseDTO> answers;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
