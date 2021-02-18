package com.br.ng.forum.domains.user.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.domains.question.topic.web.TopicService;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.br.ng.forum.domains.user.web.viewmodel.UserVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid UserVM userVM, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return register(userVM);
        }

        userService.save(userVM);
        attributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(){
        UUID userHash = loginService.authenticated().getHash();

        Optional<UserVM> userVM = userService.getEnabledForEditing(userHash);
        List<TopicVM> userTopicsVM = topicService.getAllAsList(userHash);

        ModelAndView mv = new ModelAndView("user/edit");
        mv.addObject("userVM", userVM.get());
        mv.addObject("userTopicsVM", userTopicsVM);

        return mv;
    }

    // update

    // close account


    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView register(UserVM userVM){
        return new ModelAndView("register");
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
