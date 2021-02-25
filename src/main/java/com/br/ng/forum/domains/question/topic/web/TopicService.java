package com.br.ng.forum.domains.question.topic.web;

import com.br.ng.forum.common.CRUDApplicationService;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;

import org.springframework.data.domain.Page;

public interface TopicService extends CRUDApplicationService<TopicVM, Topic> {
    
    Page<Topic> pageable(Integer page, Integer linesPerPage);
}
