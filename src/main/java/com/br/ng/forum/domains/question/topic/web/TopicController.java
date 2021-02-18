package com.br.ng.forum.domains.question.topic.web;


import java.util.Optional;

import javax.validation.Valid;

import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.devskiller.friendly_id.FriendlyId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TopicController {
    
    // @RequestMapping("/topics/{id}")
    // public ModelAndView show(@PathVariable("id") UUID id, AnswerRequestDTO answerRequestDTO){
    //     TopicResponseDTO topicDTO = modelMapper.map(topicService.findById(id), TopicResponseDTO.class);
    //     ModelAndView mv = new ModelAndView("topic/list");
    //     mv.addObject("topic", topicDTO);
    //     return mv;
    // }

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics/create")
    public ModelAndView edit(TopicVM topicVM){
        System.out.println(topicVM);
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

    @RequestMapping("/topics/edit/{friendlyHash}")
    public ModelAndView find(@PathVariable String friendlyHash){
        Optional<TopicVM> topicVM = topicService.getEnabledForEditing(FriendlyId.toUuid(friendlyHash));
        return edit(topicVM.get());
    }


    // @RequestMapping(path = "/topics/update", method = RequestMethod.POST)
    // public ModelAndView addAnswer(@Valid TopicRequestDTO topicRequestDTO, BindingResult result, RedirectAttributes atributos){

    //     if (result.hasErrors()) {
    //         return edit(modelMapper.map(topicRequestDTO, TopicSimpleResponseDTO.class));
	// 	}

    //     ModelAndView mv = new ModelAndView("redirect:/topics/"+topicRequestDTO.getId().toString());
    //     Topic post = modelMapper.map(topicRequestDTO, Topic.class);
    //     topicService.update(post);
        
    //     return mv;
    // }



}
