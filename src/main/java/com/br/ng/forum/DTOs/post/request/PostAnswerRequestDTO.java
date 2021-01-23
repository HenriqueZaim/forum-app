package com.br.ng.forum.DTOs.post.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAnswerRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Campo 'texto' não pode estar em branco!")
    private String text;
    private Long userId;

}
