package com.br.ng.forum.controllers;

import com.br.ng.forum.models.Image;
import com.br.ng.forum.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private UserService userService;
    
    // @RequestMapping(name = "topics/{id}", method = RequestMethod.POST)
    // public Image uploadTopicImage(@PathVariable Long id, @RequestParam("files[]") MultipartFile[] files){
        
    // }

    @RequestMapping(name = "users/{id}", method = RequestMethod.POST)
    public Image uploadUserImage(@PathVariable Long id, @RequestParam("files[]") MultipartFile[] files){
        userService.addImage(id, files[0]);
        return new Image(files[0].getOriginalFilename());
    }
}
