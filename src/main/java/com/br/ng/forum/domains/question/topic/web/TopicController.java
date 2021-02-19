package com.br.ng.forum.domains.question.topic.web;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.br.ng.forum.common.CRUDController;
import com.br.ng.forum.common.CRUDControllerInitializer;
import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.domains.question.answer.web.AnswerService;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.devskiller.friendly_id.FriendlyId;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(TopicController.BASE_URL)
public class TopicController extends CRUDController<TopicVM, Topic>{

    public static final String BASE_PATH = "topic";
    public static final String BASE_URL = "/topic";

    private final TopicService topicService;
    private final AnswerService answerService;
    private final LoginService loginService;

    public TopicController(TopicService topicService, AnswerService answerService, LoginService loginService) {
        super(CRUDControllerInitializer
        .<TopicVM, Topic>builder()
            .basePath(BASE_PATH)
            .baseURL(BASE_URL)
            .CRUDApplicationService(topicService)
        .build());
        this.topicService = topicService;
        this.answerService = answerService;
        this.loginService = loginService;
    }

    @Override
    public ModelAndView search(RedirectAttributes redirectAttributes, HttpSession session) {
        if(null != loginService.authenticated()){
            return new ModelAndView("redirect:/user/");
        }

        return new ModelAndView("redirect:/");
    }

    @Override
    public ModelAndView save(@Valid TopicVM viewModel, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, HttpSession session) {
        
        if (bindingResult.hasErrors()) {
            return getEditModelAndView(viewModel);
        }

        topicService.save(viewModel);
        return new ModelAndView("redirect:/user");
    }

    @Override
    public ModelAndView edit(@PathVariable String friendlyHash, RedirectAttributes redirectAttributes,
            HttpSession session) {
                Optional<TopicVM> topicVM = topicService.getEnabledForEditing(FriendlyId.toUuid(friendlyHash));
                ModelAndView mv = new ModelAndView("topic/search");
                mv.addObject("answersVM", answerService.findAllByTopicHash(topicVM.get().getHash()));
                mv.addObject("viewModel", topicVM.get());
                return mv;
    }


}
