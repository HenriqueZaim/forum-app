package com.br.ng.forum.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends DomainEntity{

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private User user;

    @OneToMany()
    private List<Answer> answers;

    @ManyToOne
    private Post post;
}
