package com.br.ng.forum.models;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public class DomainEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String name;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
