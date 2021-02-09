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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends NamedEntity{

    private static final long serialVersionUID = 1L;

    private String text;
    private Long upvotes;
    private Long visualizations;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

}
