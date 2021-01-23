package com.br.ng.forum.controllers;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.br.ng.forum.DTOs.request.UserRequestDTO;
import com.br.ng.forum.DTOs.response.PostResponseDTO;
import com.br.ng.forum.models.Post;
import com.br.ng.forum.models.User;
import com.br.ng.forum.services.PostService;
import com.br.ng.forum.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid UserRequestDTO userRequestDTO, BindingResult result){
        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            System.err.println("enois validacao");
            mv.setViewName("register");
            return mv;
        }
        userService.save(modelMapper.map(userRequestDTO, User.class));
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model){
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        model.addAttribute("user", userRequestDTO);
        return "register";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        
        ModelAndView mv = new ModelAndView();
        Page<Post> posts = postService.search(page, size);
        List<PostResponseDTO> postsDTO = posts.getContent().stream()
            .map(post -> modelMapper.map(post, PostResponseDTO.class))
            .collect(Collectors.toList());

        int totalPages = posts.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
                mv.addObject("pages", pageNumbers);
        }

        mv.addObject("currentPage", posts.getNumber());
        mv.addObject("totalPages", totalPages);
        mv.addObject("posts",  postsDTO);
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(path = "/post/new", method = RequestMethod.GET)
    public String newPost(){
        return "post/edit";
    }
}
