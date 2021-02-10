package com.br.ng.forum.repositories;

import java.util.UUID;

import com.br.ng.forum.models.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID>{
    
}
