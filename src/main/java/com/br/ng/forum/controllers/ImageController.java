package com.br.ng.forum.controllers;


import com.br.ng.forum.models.Image;
import com.br.ng.forum.services.UserService;
import com.br.ng.forum.services.storage.ImageReader;
import com.devskiller.friendly_id.FriendlyId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private ImageReader imageReader;  

    @PostMapping("/users/{id}")
    @ResponseBody
    public Image uploadUserImage(@PathVariable String id, @RequestParam("files[]") MultipartFile[] files){
        userService.addImage(FriendlyId.toUuid(id), files[0]);
        return new Image(files[0].getOriginalFilename());
    }

    @GetMapping("/{name:.*}")
    @ResponseBody
    public byte[] getImage(@PathVariable String name){
        return imageReader.getImage(name);
    }
}
