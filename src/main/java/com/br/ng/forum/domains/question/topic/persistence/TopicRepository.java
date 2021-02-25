package com.br.ng.forum.domains.question.topic.persistence;

import java.util.List;
import java.util.UUID;

import com.br.ng.forum.common.CRUDRepository;
import com.br.ng.forum.domains.question.topic.domain.Topic;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CRUDRepository<Topic>, PagingAndSortingRepository<Topic, Long>{

    List<Topic> findByUserHash(UUID hash);

    // Page<Topic> findAll(Pageable pageRequest);
}
