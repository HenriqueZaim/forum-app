package com.br.ng.forum.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class User extends NamedEntity{

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
    
    @OneToMany(mappedBy = "user")
    private List<Topic> posts;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

}
