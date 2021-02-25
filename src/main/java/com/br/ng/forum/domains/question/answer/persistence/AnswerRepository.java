package com.br.ng.forum.domains.question.answer.persistence;

import java.util.List;
import java.util.UUID;

import com.br.ng.forum.common.CRUDRepository;
import com.br.ng.forum.domains.question.answer.domain.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CRUDRepository<Answer> ,JpaRepository<Answer, Long>{
    
    List<Answer> findAllByTopicHash(UUID hash);
}
