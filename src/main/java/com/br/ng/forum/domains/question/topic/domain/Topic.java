package com.br.ng.forum.domains.question.topic.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.br.ng.forum.domains.question.QuestionLine;
import com.br.ng.forum.domains.question.answer.domain.Answer;
import com.br.ng.forum.domains.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends QuestionLine{

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(nullable = false)
    private String title;

    @Basic
    private String image;

    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

}
