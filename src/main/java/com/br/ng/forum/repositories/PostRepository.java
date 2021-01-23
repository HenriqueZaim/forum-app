package com.br.ng.forum.repositories;


import java.util.List;

import javax.transaction.Transactional;

import com.br.ng.forum.models.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>{

    @Transactional
    Page<Post> findByTitleNotNull(Pageable pageRequest);

    @Transactional
    List<Post> findByUserId(Long id);
}
