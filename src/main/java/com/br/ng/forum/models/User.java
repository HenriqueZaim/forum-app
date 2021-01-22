package com.br.ng.forum.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User extends DomainEntity{

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String email;
    
    private String password;
    private String role;
    private boolean active;
    
    @OneToMany(mappedBy = "user")
    private List<Post> posts;


}
