package com.br.ng.forum.services;

import java.util.List;

import com.br.ng.forum.models.Post;
import com.br.ng.forum.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    // Salvar

    // Alterar

    // Excluir

    // Recuperar 1

    // Recuperar lista
    public Page<Post> search(Integer page, Integer linesPerPage){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.DESC,"createdAt"));
        Page<Post> list = postRepository.findByTitleNotNull(pageRequest);
        return list;
    }

}
