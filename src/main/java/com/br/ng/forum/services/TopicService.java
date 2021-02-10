package com.br.ng.forum.services;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.models.Topic;
import com.br.ng.forum.models.User;
import com.br.ng.forum.repositories.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    
    @Autowired
    private TopicRepository postRepository;

    @Autowired
    private LoginService loginService;

    public Topic save(Topic post){
        User user = new User();
        user.setId(loginService.authenticated().getId());
        post.setUser(user);
        post.setCreatedAt(OffsetDateTime.now());
        return postRepository.save(post);
    }

    public Topic update(Topic post){
        post.setCreatedAt(findById(post.getId()).getCreatedAt());
        post.setUpdatedAt(OffsetDateTime.now());
        User user = new User();
        user.setId(loginService.authenticated().getId());
        post.setUser(user);
        return postRepository.save(post);
    }

    public Topic findById(UUID id){
        return postRepository.findById(id).get();
    }

    public Page<Topic> search(Integer page, Integer linesPerPage){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.DESC,"createdAt"));
        Page<Topic> list = postRepository.findAll(pageRequest);
        return list;
    }

    public List<Topic> findByUserId(){
        return postRepository.findByUserId(loginService.authenticated().getId());
    }

}
