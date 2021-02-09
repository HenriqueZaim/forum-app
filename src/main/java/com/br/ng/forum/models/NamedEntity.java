package com.br.ng.forum.models;

import java.time.OffsetDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class NamedEntity extends DomainEntity{
    
    private static final long serialVersionUID = 1L;

    private String name;
    private String image;
    private OffsetDateTime deletedAt;
}
