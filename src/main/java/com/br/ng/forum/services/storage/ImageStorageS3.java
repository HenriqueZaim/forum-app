package com.br.ng.forum.services.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.Instant;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.br.ng.forum.config.exceptions.FileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("test")
@Component
public class ImageStorageS3 implements ImageStorage {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Override
    public String save(MultipartFile image) {
        try {
            String fileName = image.getOriginalFilename().replaceAll(" ", "-") + " - " + Timestamp.from(Instant.now());
            InputStream is = image.getInputStream();
            String contentType = image.getContentType();
            return uploadFile(is, fileName, contentType).toString();

        } catch (IOException e) {
            throw new FileException("Erro de IO");
        }
    }

    public String getUrl(String url) {
        return null;
    }

    public URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            s3Client.putObject(bucketName, fileName, is, meta);
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }
    
}
