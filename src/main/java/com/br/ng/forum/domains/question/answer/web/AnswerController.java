package com.br.ng.forum.domains.question.answer.web;

import javax.validation.Valid;

import com.br.ng.forum.domains.question.answer.web.viewmodel.AnswerVM;
import com.br.ng.forum.domains.question.topic.web.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnswerController {
    
    @Autowired
    private AnswerService answerService;

}
