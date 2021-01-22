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
@AllArgsConstructor
@NoArgsConstructor
public class Post extends NamedEntity{

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Answer> answers;
}
