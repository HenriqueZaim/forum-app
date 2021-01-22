package com.br.ng.forum.controllers;

import javax.validation.Valid;

import com.br.ng.forum.DTOs.request.UserRequestDTO;
import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.models.User;
import com.br.ng.forum.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid UserRequestDTO userRequestDTO){
        ModelAndView mv = new ModelAndView();
        userService.save(modelMapper.map(userRequestDTO, User.class));
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model){
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        model.addAttribute("user", userRequestDTO);
        return "user/register";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(){
        System.out.println(loginService.authenticated());
        return "home";
    }
}
