package com.br.ng.forum.domains.user.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.br.ng.forum.domains.DomainEntity;
import com.br.ng.forum.domains.question.answer.domain.Answer;
import com.br.ng.forum.domains.question.topic.domain.Topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends DomainEntity{

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(nullable = false, unique = true)
    private String name;

    @Basic
    @Column(nullable = false, unique = true)
    private String email;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    @Column(nullable = false)
    private String role = "USER";
    
    @OneToMany(mappedBy = "user")
    private List<Topic> topics;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    private String image;

}
