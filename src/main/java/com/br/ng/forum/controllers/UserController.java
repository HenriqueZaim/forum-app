package com.br.ng.forum.controllers;

import javax.validation.Valid;

import com.br.ng.forum.DTOs.UserDTO;
import com.br.ng.forum.models.User;
import com.br.ng.forum.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid UserDTO userDTO){
        ModelAndView mv = new ModelAndView();
        userService.save(modelMapper.map(userDTO, User.class));
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(){
        return "home";
    }
}
