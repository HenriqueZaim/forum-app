package com.br.ng.forum.domains.question.topic.web;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.br.ng.forum.domains.question.answer.web.AnswerService;
import com.br.ng.forum.domains.question.answer.web.viewmodel.AnswerVM;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.devskiller.friendly_id.FriendlyId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TopicController {
    
    @Autowired
    private TopicService topicService;

    @Autowired
    private AnswerService answerService;

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

    @RequestMapping("/topics/create")
    public ModelAndView edit(TopicVM topicVM){
        return new ModelAndView("topic/edit").addObject("topicVM", topicVM);
    }

    @RequestMapping(path = "/topics/save", method = RequestMethod.POST)
    public ModelAndView store(@Valid TopicVM topicVM, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return edit(topicVM);
		}

        topicService.save(topicVM);
        // attributes.addAttribute("message", "Tópico criado com sucesso!"); TODO: must be dynamic
        return new ModelAndView("redirect:/topics/create");
    }

    // ! Apagar
    @RequestMapping("/topics/edit/{friendlyHash}")
    public ModelAndView find(@PathVariable String friendlyHash){
        Optional<TopicVM> topicVM = topicService.getEnabledForEditing(FriendlyId.toUuid(friendlyHash));
        return edit(topicVM.get());
    }

    // * Posso ver mesmo se estiver inativado, 
    // *     porém não posso editar e/ou responder
    @RequestMapping("/topics/{friendlyHash}")
    public ModelAndView search(@PathVariable String friendlyHash, AnswerVM answerVM){
        Optional<TopicVM> topicVM = topicService.getEnabledForEditing(FriendlyId.toUuid(friendlyHash));
        ModelAndView mv = new ModelAndView("topic/list");
        mv.addObject("answersVM", answerService.findAllByTopicHash(topicVM.get().getHash()));
        mv.addObject("answerVM", answerVM);
        mv.addObject("topicVM", topicVM.get());
        return mv;
    }

    @RequestMapping(path = "/topics/{friendlyHash}/answers/save", method = RequestMethod.POST)
    public ModelAndView store(@Valid AnswerVM answerVM, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return search(answerVM.getTopicFriendlyHash(), answerVM);
		}

        answerService.save(answerVM);
        return new ModelAndView("redirect:/profile");
    }


}
