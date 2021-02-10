package com.br.ng.forum.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class TopicController {
    
    // @Autowired
    // private ModelMapper modelMapper;

    // @Autowired
    // private TopicService postService;

    // @PreAuthorize("isAuthenticated()")
    // @RequestMapping(path = "/posts/new", method = RequestMethod.GET)
    // public String newPost(Model model){
    //     TopicRequestDTO postDTO =  new TopicRequestDTO();
    //     model.addAttribute("post", postDTO);
    //     return "post/edit";
    // }

    // @RequestMapping(path = "/posts/update/{id}", method = RequestMethod.GET)
    // public String updatePost(@PathVariable UUID id, Model model){
    //     TopicRequestDTO postDTO =  modelMapper.map(postService.findById(id), TopicRequestDTO.class);
    //     model.addAttribute("post", postDTO);
    //     return "post/edit";
    // }

    // @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    // public ModelAndView findPost(@PathVariable("id") Topic post){
    //     PostResponseDTO postDTO = modelMapper.map(post, PostResponseDTO.class);
    //     ModelAndView mv = new ModelAndView("post/list");
    //     PostAnswerRequestDTO postAnswerRequestDTO = new PostAnswerRequestDTO();
    //     mv.addObject("newanswer", postAnswerRequestDTO);
    //     mv.addObject("post", postDTO);
    //     return mv;
    // }

    // @PreAuthorize("isAuthenticated()")
    // @RequestMapping(path = "/posts/update", method = RequestMethod.POST)
    // public ModelAndView addAnswer(@Valid TopicRequestDTO postRequestDTO, BindingResult result, RedirectAttributes atributos){
    //     ModelAndView mv = new ModelAndView("redirect:/posts/update/"+postRequestDTO.getId().toString());

    //     if (result.hasErrors()) {
    //         mv.addObject("erros", "Erros no formulário");
    //         return mv;
	// 	}

    //     Topic post = modelMapper.map(postRequestDTO, Topic.class);
    //     postService.update(post);
        
    //     return mv;
    // }

    // @PreAuthorize("isAuthenticated()")
    // @RequestMapping(path = "/posts/{id}/newanswher", method = RequestMethod.POST)
    // public ModelAndView addAnswer(@Valid PostAnswerRequestDTO postAnswerRequestDTO, @PathVariable UUID id, BindingResult result){
    //     ModelAndView mv = new ModelAndView("redirect:/posts/"+id);
    //     if (result.hasErrors()) {
    //         mv.addObject("erros", "Erros no formulário");
    //         return mv;
	// 	}
    //     Topic post = modelMapper.map(postAnswerRequestDTO, Topic.class);
    //     postService.save(post, id);
        
    //     return mv;
    // }

    // @PreAuthorize("isAuthenticated()")
    // @RequestMapping(path = "/posts/save", method = RequestMethod.POST)
    // public ModelAndView savePost(@Valid TopicRequestDTO postRequestDTO, BindingResult result){
    //     ModelAndView mv = new ModelAndView("redirect:/profile");
    //     if (result.hasErrors()) {
    //         mv.addObject("erros", "Erros no formulário");
    //         return mv;
	// 	}
    //     Topic post = modelMapper.map(postRequestDTO, Topic.class);
    //     postService.save(post);
        
    //     return mv;
    // }


}
