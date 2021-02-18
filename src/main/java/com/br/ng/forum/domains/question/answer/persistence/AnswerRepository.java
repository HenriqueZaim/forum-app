package com.br.ng.forum.domains.question.answer.persistence;

import java.util.UUID;

import com.br.ng.forum.domains.question.answer.domain.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID>{
    
}
