package com.br.ng.forum.controllers;


import javax.validation.Valid;

import com.br.ng.forum.DTOs.post.request.PostAnswerRequestDTO;
import com.br.ng.forum.DTOs.post.request.PostRequestDTO;
import com.br.ng.forum.DTOs.post.response.PostResponseDTO;
import com.br.ng.forum.models.Post;
import com.br.ng.forum.services.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostService postService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/posts/new", method = RequestMethod.GET)
    public String newPost(Model model){
        PostRequestDTO postDTO =  new PostRequestDTO();
        model.addAttribute("post", postDTO);
        return "post/edit";
    }

    @RequestMapping(path = "/posts/update/{id}", method = RequestMethod.GET)
    public String updatePost(@PathVariable Long id, Model model){
        PostRequestDTO postDTO =  modelMapper.map(postService.findById(id), PostRequestDTO.class);
        model.addAttribute("post", postDTO);
        return "post/edit";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView findPost(@PathVariable("id") Post post){
        PostResponseDTO postDTO = modelMapper.map(post, PostResponseDTO.class);
        ModelAndView mv = new ModelAndView("post/list");
        PostAnswerRequestDTO postAnswerRequestDTO = new PostAnswerRequestDTO();
        mv.addObject("newanswer", postAnswerRequestDTO);
        mv.addObject("post", postDTO);
        return mv;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/posts/update", method = RequestMethod.POST)
    public ModelAndView addAnswer(@Valid PostRequestDTO postRequestDTO, BindingResult result, RedirectAttributes atributos){
        ModelAndView mv = new ModelAndView("redirect:/posts/update/"+Long.toString(postRequestDTO.getId()));

        if (result.hasErrors()) {
            mv.addObject("erros", "Erros no formulário");
            return mv;
		}

        Post post = modelMapper.map(postRequestDTO, Post.class);
        postService.update(post);
        
        return mv;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/posts/{id}/newanswher", method = RequestMethod.POST)
    public ModelAndView addAnswer(@Valid PostAnswerRequestDTO postAnswerRequestDTO, @PathVariable Long id, BindingResult result){
        ModelAndView mv = new ModelAndView("redirect:/posts/"+id);
        if (result.hasErrors()) {
            mv.addObject("erros", "Erros no formulário");
            return mv;
		}
        Post post = modelMapper.map(postAnswerRequestDTO, Post.class);
        postService.save(post, id);
        
        return mv;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/posts/save", method = RequestMethod.POST)
    public ModelAndView savePost(@Valid PostRequestDTO postRequestDTO, BindingResult result){
        ModelAndView mv = new ModelAndView("redirect:/profile");
        if (result.hasErrors()) {
            mv.addObject("erros", "Erros no formulário");
            return mv;
		}
        Post post = modelMapper.map(postRequestDTO, Post.class);
        postService.save(post);
        
        return mv;
    }


}
