package com.br.ng.forum.models;

import java.util.List;

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
    
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    private String email;
    private String password;
    private String roles;
    private boolean active;
}
