package com.br.ng.forum.domains.question.answer.persistence;

import com.br.ng.forum.domains.question.answer.domain.Answer;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
@Service
public class AnswerPersistenceService{
    
    interface ExcludeFromDelegation {
        Answer save(Answer answer);
    }

    @Delegate(excludes = ExcludeFromDelegation.class)
    private final AnswerRepository answerRepository;

    public Answer save(Answer answer) {
        return this.answerRepository.save(answer);
    }
}
