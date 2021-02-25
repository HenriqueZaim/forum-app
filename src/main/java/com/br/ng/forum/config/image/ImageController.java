package com.br.ng.forum.config.image;


import com.br.ng.forum.config.image.service.ImageReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired(required = false)
    private ImageReader imageReader;  

    // @PostMapping("/users")
    // @ResponseBody
    // public String uploadUserImage(@RequestParam("id") Long id, @RequestParam("files[]") MultipartFile[] files){
    //     userService.addImage(id, files[0]);
    //     return files[0].getOriginalFilename();
    // }

    @GetMapping("/{name:.*}")
    @ResponseBody
    public byte[] getImage(@PathVariable String name){
        return imageReader.getImage(name);
    }
}
