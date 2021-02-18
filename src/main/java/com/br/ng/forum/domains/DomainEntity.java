package com.br.ng.forum.domains;

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

import com.devskiller.friendly_id.FriendlyId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
public abstract class DomainEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
    protected Long id;
    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    protected UUID hash;

    public DomainEntity() {
        this.hash = UUID.randomUUID();
    }

    @PrePersist
    protected void prePersist(){
        setCreatedAt(OffsetDateTime.now());
    }

    @PreUpdate
    protected void preUpdate(){
        setUpdatedAt(OffsetDateTime.now());
    }

    public String getFriendlyHash(){
        return FriendlyId.toFriendlyId(this.hash);
    }

}
