package com.br.ng.forum.controllers;

import javax.validation.Valid;

import com.br.ng.forum.DTOs.user.UserRequestDTO;
import com.br.ng.forum.DTOs.user.UserResponseDTO;
import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.models.User;
import com.br.ng.forum.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;
    
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid UserRequestDTO userRequestDTO, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return register(userRequestDTO);
        }

        userService.save(modelMapper.map(userRequestDTO, User.class));
        attributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(){
        ModelAndView mv = new ModelAndView("/user/edit");
        UserResponseDTO user = modelMapper.map(
            userService.findById(loginService.authenticated().getId()), 
            UserResponseDTO.class);
        mv.addObject("user", user);
        return mv;
    }

    // update

    // close account


    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView register(UserRequestDTO userRequestDTO){
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
