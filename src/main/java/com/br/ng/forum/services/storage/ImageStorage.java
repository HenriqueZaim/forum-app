package com.br.ng.forum.services.storage;


import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {
    
    public String save(MultipartFile image);
    public String getUrl(String name);
}
