package com.br.ng.forum.DTOs.user;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSimpleResponseDTO {
    
    private UUID id;
    private String email;
    private String name;
    private String image;
    
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
}
