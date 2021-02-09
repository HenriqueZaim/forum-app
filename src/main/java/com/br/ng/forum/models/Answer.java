package com.br.ng.forum.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class Answer extends DomainEntity {

    private static final long serialVersionUID = 1L;

    private String text;
    private Long upvotes;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;
    
    @ManyToOne
    @JoinColumn(name = "parent_answer_id", nullable = true)
    private Answer parentAnswer;

    @OneToMany(mappedBy = "parentAnswer")
    private List<Answer> answers;

}
