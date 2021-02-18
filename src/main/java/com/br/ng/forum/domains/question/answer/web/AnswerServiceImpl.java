package com.br.ng.forum.domains.question.answer.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.br.ng.forum.domains.question.answer.domain.Answer;
import com.br.ng.forum.domains.question.answer.persistence.AnswerPersistenceService;
import com.br.ng.forum.domains.question.answer.web.viewmodel.AnswerVM;
import com.br.ng.forum.domains.question.topic.persistence.TopicPersistenceService;
import com.br.ng.forum.domains.user.persistence.UserPersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerPersistenceService answerPersistenceService;
    @Autowired
    private UserPersistenceService userPersistenceService;
    @Autowired
    private TopicPersistenceService topicPersistenceService;

    @Override
    public Answer save(AnswerVM vm) {
        Answer answer = new Answer();

        if (vm.isUpdate()) {
            Optional<Answer> optionalAnswer = this.answerPersistenceService.findByHash(vm.getHash(), Answer.class);
            if (optionalAnswer.isPresent()) {
                answer = optionalAnswer.get();
            }
        }

        vm.fill(answer, answerPersistenceService, topicPersistenceService, userPersistenceService);

        this.answerPersistenceService.save(answer);
        return answer;
    }

    @Override
    public Optional<AnswerVM> getEnabledForEditing(UUID hash) {
        return null;
    }

    @Override
    public void removeLogicallyByHash(UUID hash) {

    }

    @Override
    public List<AnswerVM> getAllAsList(UUID hash) {
        return null;
    }

    @Override
    public List<AnswerVM> findAllByTopicHash(UUID hash) {
        return answerPersistenceService.findAllByTopicHash(hash)
                .stream()
                .map(answer -> AnswerVM.from(answer))
                .collect(Collectors.toList());
    }
    
}
