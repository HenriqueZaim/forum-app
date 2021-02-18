package com.br.ng.forum.common;

import javax.validation.Valid;

import com.br.ng.forum.domains.user.web.UserService;
import com.br.ng.forum.domains.user.web.viewmodel.UserVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid UserVM userVM, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return register(userVM);
        }

        userService.save(userVM);
        attributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView register(UserVM userVM){
        return new ModelAndView("register");
    }

}
