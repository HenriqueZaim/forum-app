package com.br.ng.forum.DTOs.topic;

import java.io.Serializable;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Campo 'Título' não pode estar em branco!")
    private String title;

    @NotBlank(message = "Campo 'texto' não pode estar em branco!")
    private String text;

}
