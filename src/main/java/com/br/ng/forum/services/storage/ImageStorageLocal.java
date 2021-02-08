package com.br.ng.forum.services.storage;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("test")
@Component
public class ImageStorageLocal implements ImageStorage {

    @Override
    public String save(MultipartFile image) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUrl(String url) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
