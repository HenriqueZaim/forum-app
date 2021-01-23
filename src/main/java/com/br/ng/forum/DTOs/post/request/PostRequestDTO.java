package com.br.ng.forum.DTOs.post.request;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;

    @NotBlank(message = "Campo 'Título' não pode estar em branco!")
    private String title;

    @NotBlank(message = "Campo 'texto' não pode estar em branco!")
    private String text;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
