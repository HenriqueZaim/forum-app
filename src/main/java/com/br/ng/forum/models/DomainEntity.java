package com.br.ng.forum.models;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
public abstract class DomainEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
    protected UUID id;
    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    @PrePersist
    protected void prePersist(){
        setCreatedAt(OffsetDateTime.now());
    }

    @PreUpdate
    protected void preUpdate(){
        setUpdatedAt(OffsetDateTime.now());
    }

}
