package com.br.ng.forum.services;


import java.util.List;

import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.models.Post;
import com.br.ng.forum.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LoginService loginService;

    // Salvar

    // Recuperar 1

    // Recuperar lista
    public Page<Post> search(Integer page, Integer linesPerPage){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.DESC,"createdAt"));
        Page<Post> list = postRepository.findByTitleNotNull(pageRequest);
        return list;
    }

    public List<Post> findByUserId(){
        return postRepository.findByUserId(loginService.authenticated().getId());
    }

}
