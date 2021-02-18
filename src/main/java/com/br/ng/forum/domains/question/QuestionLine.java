package com.br.ng.forum.domains.question;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.br.ng.forum.domains.DomainEntity;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class QuestionLine extends DomainEntity{

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(nullable = false)
    private String text;

    @Basic
    @Column(nullable = false)
    private Long upvotes = 0L;

    @Basic
    @Column(nullable = false)
    private Long visualizations = 0L;
    
    
}
