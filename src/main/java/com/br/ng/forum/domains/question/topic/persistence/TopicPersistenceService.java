package com.br.ng.forum.domains.question.topic.persistence;

import com.br.ng.forum.domains.question.topic.domain.Topic;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
@Service
public class TopicPersistenceService {
    
    interface ExcludeFromDelegation {
        Topic save(Topic topic);
    }

    @Delegate(excludes = ExcludeFromDelegation.class)
    private final TopicRepository topicRepository;

    public Topic save(Topic topic){
        return this.topicRepository.save(topic);
    }
}
