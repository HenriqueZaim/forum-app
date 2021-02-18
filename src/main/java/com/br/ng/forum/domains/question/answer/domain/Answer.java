package com.br.ng.forum.domains.question.answer.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.br.ng.forum.domains.question.QuestionLine;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends QuestionLine {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;
    
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_answer_id", nullable = true)
    private Answer parentAnswer;

    @OneToMany(mappedBy = "parentAnswer")
    private List<Answer> answers;

}
