package com.br.ng.forum.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
public class Post extends DomainEntity{

    private static final long serialVersionUID = 1L;

    private String title;
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_post_id", nullable = true)
    private Post parentPost;

    @OneToMany(mappedBy = "parentPost")
    private List<Post> answers;
}
