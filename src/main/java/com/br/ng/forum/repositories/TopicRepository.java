package com.br.ng.forum.repositories;


import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.br.ng.forum.models.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends PagingAndSortingRepository<Topic, UUID>{

    @Transactional
    List<Topic> findByUserId(UUID id);

    Page<Topic> findAll(Pageable pageRequest);
}
