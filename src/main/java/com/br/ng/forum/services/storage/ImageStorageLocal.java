package com.br.ng.forum.services.storage;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("prod")
@Component
public class ImageStorageLocal implements ImageStorage, ImageReader {

    private Path local;

    public ImageStorageLocal(){
        this.local = FileSystems.getDefault().getPath(System.getenv("HOME"), ".images");
        try {
            Files.createDirectories(this.local);
        } catch (IOException e) {
            throw new RuntimeException("Erro criando pasta de fotos");
        }
    }

    @Override
    public String save(MultipartFile image) {
        String name = image.getOriginalFilename();
        try {
            image.transferTo(new File(
                this.local.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + name
            ));
        } catch (IOException e) {
            throw new RuntimeException("Erro salvando foto");
        }
        return name;
    }

    @Override
    public byte[] getImage(String name) {
        try {
            return Files.readAllBytes(this.local.resolve(name));
        } catch (IOException e) {
            throw new RuntimeException("Erro recuperando foto", e);
        }
    }

    @Override
    public String getUrl(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
