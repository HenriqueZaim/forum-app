package com.br.ng.forum.controllers;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.br.ng.forum.DTOs.post.response.PostResponseDTO;
import com.br.ng.forum.DTOs.user.request.UserRequestDTO;
import com.br.ng.forum.models.Topic;
import com.br.ng.forum.models.User;
import com.br.ng.forum.services.TopicService;
import com.br.ng.forum.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService postService;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid UserRequestDTO userRequestDTO, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return register(userRequestDTO);
        }

        userService.save(modelMapper.map(userRequestDTO, User.class));
        attributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }


    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView register(UserRequestDTO userRequestDTO){
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        
        ModelAndView mv = new ModelAndView();
        Page<Topic> posts = postService.search(page, size);
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



    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String profile(Model model){
        List<PostResponseDTO> posts = postService.findByUserId()
                                .stream()
                                    .map(obj -> modelMapper.map(obj, PostResponseDTO.class))
                                    .collect(Collectors.toList());
        model.addAttribute("posts", posts);
        return "user/edit";
    }
}
