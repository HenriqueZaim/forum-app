package com.br.ng.forum.domains.question.answer.web;

import java.util.List;
import java.util.UUID;

import com.br.ng.forum.common.CRUDApplicationService;
import com.br.ng.forum.domains.question.answer.domain.Answer;
import com.br.ng.forum.domains.question.answer.web.viewmodel.AnswerVM;


public interface AnswerService extends CRUDApplicationService<AnswerVM, Answer>{
    
    List<AnswerVM> findAllByTopicHash(UUID hash);
}
