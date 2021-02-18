package com.br.ng.forum.config.image.service;


import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {
    
    public String save(MultipartFile image);
    public String getUrl(String name);
}
