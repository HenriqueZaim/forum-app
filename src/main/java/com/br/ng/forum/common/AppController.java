package com.br.ng.forum.common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.web.TopicService;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.br.ng.forum.domains.user.web.UserService;
import com.br.ng.forum.domains.user.web.viewmodel.UserVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;
    
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

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "page", defaultValue = "0") Integer page, 
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {
        
        ModelAndView mv = new ModelAndView();
        Page<Topic> topics = topicService.pageable(page, size);
        List<TopicVM> topicsVM = topics.getContent().stream()
            .map(topic -> TopicVM.from(topic))
            .collect(Collectors.toList());

        int totalPages = topics.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
                mv.addObject("pages", pageNumbers);
        }

        mv.addObject("currentPage", topics.getNumber());
        mv.addObject("totalPages", totalPages);
        mv.addObject("topicsVM",  topicsVM);
        mv.setViewName("home");
        return mv;
    }

}
