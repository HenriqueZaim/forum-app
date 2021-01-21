package com.br.ng.forum.models;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NamedEntity extends DomainEntity{

    private static final long serialVersionUID = 1L;
    private String name;
}
