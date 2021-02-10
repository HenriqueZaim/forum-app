package com.br.ng.forum.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.br.ng.forum.DTOs.topic.TopicResponseDTO;
import com.br.ng.forum.models.Topic;
import com.br.ng.forum.services.TopicService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TopicService topicService;
    
    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        
        ModelAndView mv = new ModelAndView();
        Page<Topic> topics = topicService.search(page, size);
        List<TopicResponseDTO> topicsDTO = topics.getContent().stream()
            .map(topic -> modelMapper.map(topic, TopicResponseDTO.class))
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
        mv.addObject("topics",  topicsDTO);
        mv.setViewName("home");
        return mv;
    }


}
