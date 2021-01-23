package com.br.ng.forum.services;


import java.time.OffsetDateTime;
import java.util.List;

import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.models.Post;
import com.br.ng.forum.models.User;
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

    public Post save(Post post, Long postParentId){
        Post parentPost = postRepository.findById(postParentId).get();
        post.setParentPost(parentPost);
        post.setCreatedAt(OffsetDateTime.now());
        return postRepository.save(post);
    }

    public Post save(Post post){
        User user = new User();
        user.setId(loginService.authenticated().getId());
        post.setUser(user);
        post.setCreatedAt(OffsetDateTime.now());
        return postRepository.save(post);
    }

    public Post update(Post post){
        post.setCreatedAt(findById(post.getId()).getCreatedAt());
        post.setUpdatedAt(OffsetDateTime.now());
        User user = new User();
        user.setId(loginService.authenticated().getId());
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post findById(Long id){
        return postRepository.findById(id).get();
    }

    public Page<Post> search(Integer page, Integer linesPerPage){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.DESC,"createdAt"));
        Page<Post> list = postRepository.findByParentPostNull(pageRequest);
        return list;
    }

    public List<Post> findByUserId(){
        return postRepository.findByUserId(loginService.authenticated().getId());
    }

}
