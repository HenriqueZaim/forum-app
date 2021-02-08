package com.br.ng.forum.services;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Optional;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.br.ng.forum.config.exceptions.ObjectNotFoundException;
import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.models.User;
import com.br.ng.forum.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AmazonS3 s3Client;

    public User save(User user){
        user.setCreatedAt(OffsetDateTime.now());
        user.setPassword(bCrypt.encode(user.getPassword().trim()));
        return userRepository.save(user);
    }

    public void addImage(Long id, MultipartFile image){
        User user = findById(id);
        user.setImage(image.getOriginalFilename());
        userRepository.save(user);

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            metadata.setContentLength(image.getSize());
            s3Client.putObject("forum-ng", image.getOriginalFilename(), image.getInputStream(), metadata);
        } catch (AmazonClientException | IOException e) {
            throw new RuntimeException("Erro salvar arquivo s3");
        }
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User find(){
        return userRepository.findById(loginService.authenticated().getId()).get();
    }
    
}
